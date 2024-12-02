package com.demo.carrent.service;

import com.demo.carrent.common.PaymentStatus;
import com.demo.carrent.common.RentStatus;
import com.demo.carrent.dto.RentDto;
import com.demo.carrent.dto.RentUpdateDto;
import com.demo.carrent.dto.response.CreateResponse;
import com.demo.carrent.dto.response.DeleteResponse;
import com.demo.carrent.dto.response.UpdateResponse;
import com.demo.carrent.entity.Rent;
import com.demo.carrent.entity.User;
import com.demo.carrent.entity.Vehicle;
import com.demo.carrent.repository.RentRepository;
import com.demo.carrent.repository.UserRepository;
import com.demo.carrent.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentServiceImpl implements RentService{

    private final RentRepository rentRepository;

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    //creating a 'Repository' instances by constructor injection
    @Autowired
    public RentServiceImpl(RentRepository rentRepository,VehicleRepository vehicleRepository,UserRepository userRepository){
        this.rentRepository=rentRepository;
        this.vehicleRepository=vehicleRepository;
        this.userRepository=userRepository;
    }

    @Override
    @Transactional
    public CreateResponse<Rent> createRent(RentDto rentDto)throws IllegalArgumentException {

        try{
            //check if the entered timestamps are valid
            if(rentDto.getStartingDate()!=null && rentDto.getEndDate()!=null){
                if(!rentDto.getStartingDate().isBefore(rentDto.getEndDate())){
                    throw new IllegalArgumentException("Starting date must be before the ending date");
                }
            }else {
                throw new IllegalArgumentException("Starting or Ending dates should not be null");
            }

            //create a response object
            CreateResponse<Rent> response=new CreateResponse<>();

            //get vehicle and user for corresponding ids
            Vehicle vehicle=vehicleRepository.findById(rentDto.getVehicleId()).orElse(null);
            User user=userRepository.findById(rentDto.getUserId()).orElse(null);

            if(user!=null){
                if(vehicle!=null){
                    //check if the vehicle is available for rent
                    if(vehicle.getIsAvailable()){
                        Rent rent=new Rent();

                        //Deriving duration by getting date difference
                        int duration=(int)ChronoUnit.DAYS.between(rentDto.getStartingDate(),rentDto.getEndDate());

                        if(duration>30){
                            response.setStatusMessage("Duration is exceeding maximum renting period");

                            return response;
                        }

                        rent.setTotalDays(duration);
                        rent.setPrice(rent.getTotalDays()*vehicle.getRentPerDay());
                        rent.setBookingTime(LocalDateTime.now());
                        rent.setStartingDate(rentDto.getStartingDate());
                        rent.setEndDate(rentDto.getEndDate());
                        rent.setRentStatus(RentStatus.PENDING);
                        rent.setPaymentStatus(PaymentStatus.PENDING);
                        rent.setUser(user);
                        rent.setVehicle(vehicle);

                        //set vehicle availability to false
                        updateAvailability(vehicle,false);

                        rentRepository.save(rent);

                        response.setStatusMessage("Rent was created successfully");
                        response.setCreatedData(rent);

                        return response;
                    }
                    else{
                        response.setStatusMessage("Vehicle is not available for rent");
                        return response;
                    }
                }else {
                    response.setStatusMessage("Invalid vehicle Id");
                    return response;
                }
            }else {
                response.setStatusMessage("Invalid user Id");

                return response;
            }

        }catch (IllegalArgumentException e){
            throw e;
        }

    }

    @Override
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    @Override
    public Rent getRentById(Long id) {

        return rentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public UpdateResponse<Rent> updateRent(Long id, RentUpdateDto rentUpdateDto)throws IllegalArgumentException {

        //create an instance of update response for rent update
        UpdateResponse<Rent> rentUpdateResponse=new UpdateResponse<>();

        //find existing rent object
        Rent existingRent=rentRepository.findById(id).orElse(null);

        //find current vehicle
        Vehicle currentVehicle=existingRent.getVehicle();

        //find new vehicle for update rent
        Vehicle newVehicle=vehicleRepository.findById(rentUpdateDto.getVehicleId()).orElse(null);

        //check if a rent is existed for given id
        if(existingRent!=null){
           try {
               if(rentUpdateDto.getStartingDate()!=null && rentUpdateDto.getEndDate()!=null){
                   if(!rentUpdateDto.getStartingDate().isBefore(rentUpdateDto.getEndDate())){
                       throw new IllegalArgumentException("Starting date must be before the ending date");
                   }
               }else {
                   throw new IllegalArgumentException("Starting or Ending dates should not be null");
               }

               if(newVehicle!=null){

                   existingRent.setTotalDays((int)ChronoUnit.DAYS.between(rentUpdateDto.getStartingDate(),rentUpdateDto.getEndDate()));
                   existingRent.setPrice(existingRent.getTotalDays()*newVehicle.getRentPerDay());
                   existingRent.setStartingDate(rentUpdateDto.getStartingDate());
                   existingRent.setEndDate(rentUpdateDto.getEndDate());
                   existingRent.setRentStatus(RentStatus.PENDING);
                   existingRent.setPaymentStatus(PaymentStatus.PENDING);
                   existingRent.setVehicle(newVehicle);

                   Rent updatedRent= rentRepository.save(existingRent);

                   if(newVehicle!=currentVehicle){
                       //update availability of vehicle in updated rent
                       updateAvailability(newVehicle,false);

                       //update availability of
                       updateAvailability(currentVehicle,true);
                   }

                   rentUpdateResponse.setResponseMessage("Rent updated successfully");
                   rentUpdateResponse.setUpdatedData(updatedRent);

               }else{
                   rentUpdateResponse.setResponseMessage("Invalid vehicle id");
               }
           }catch (IllegalArgumentException e){
               throw e;
           }

           return rentUpdateResponse;
        }else{
            rentUpdateResponse.setResponseMessage("No rent available");

            return rentUpdateResponse;
        }
    }

    @Override
    public DeleteResponse deleteRent(Long id) {

        //find if the rent exist
        boolean isExist=rentRepository.existsById(id);

        //create instance of delete response
        DeleteResponse deleteResponse=new DeleteResponse();

        if(isExist){
            rentRepository.deleteById(id);

            deleteResponse.setStatusMessage("Rent deleted successfully");
            deleteResponse.setIsDeleted(true);

            return deleteResponse;
        }else {
            deleteResponse.setStatusMessage("No rent found");

            return deleteResponse;
        }

    }


    //update vehicle availability
    private void updateAvailability(Vehicle vehicle,Boolean isAvailable){
        vehicle.setIsAvailable(isAvailable);

        vehicleRepository.save(vehicle);
    }
}
