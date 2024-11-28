package com.demo.carrent.service;

import com.demo.carrent.dto.RentDto;
import com.demo.carrent.entity.Rent;
import com.demo.carrent.entity.User;
import com.demo.carrent.entity.Vehicle;
import com.demo.carrent.repository.RentRepository;
import com.demo.carrent.repository.UserRepository;
import com.demo.carrent.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Rent createRent(RentDto rentDto) {

        //check if the vehicle is available for the rent
        Vehicle vehicle=vehicleRepository.findById(rentDto.getVehicleId()).orElse(null);

        //get a user
        User user=userRepository.findById(rentDto.getUserId()).orElse(null);

        if(vehicle!=null && user!=null){
            if(vehicle.getIsAvailable()){
                Rent rent=new Rent();

                //set booking time
                rent.setBookingTime(LocalDateTime.now());

                //setting starting day and end day
                rent.setStartingDate(rentDto.getStartingDate());
                rent.setEndDate(rentDto.getEndDate());

                //set total days
                if(rent.getStartingDate()!=null && rent.getEndDate()!=null){
                    rent.setTotalDays((int) ChronoUnit.DAYS.between(rentDto.getStartingDate(),rentDto.getEndDate()));
                }

                //set price
                rent.setPrice(rent.getTotalDays()*vehicle.getRentPerDay());
                rent.setRentStatus(rentDto.getRentStatus());
                rent.setPaymentStatus(rentDto.getPaymentStatus());
                rent.setUser(user);
                rent.setVehicle(vehicle);

                return rentRepository.save(rent);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public List<Rent> getAllRents() {
        return List.of();
    }
}
