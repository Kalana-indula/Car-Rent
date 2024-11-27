package com.demo.carrent.service;

import com.demo.carrent.dto.VehicleDto;
import com.demo.carrent.dto.response.UpdateResponse;
import com.demo.carrent.entity.Vehicle;
import com.demo.carrent.entity.VehicleCategory;
import com.demo.carrent.repository.VehicleCategoryRepository;
import com.demo.carrent.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;
    private final VehicleCategoryRepository vehicleCategoryRepository;

    //Injecting an instance of vehicleRepository
    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepo,VehicleCategoryRepository vehicleCategoryRepo){
        this.vehicleRepository=vehicleRepo;
        this.vehicleCategoryRepository=vehicleCategoryRepo;
    }

    @Override
    public Vehicle createVehicle(VehicleDto vehicleDto) {
        //find if there are category for corresponding category id
        VehicleCategory vehicleCategory=vehicleCategoryRepository.findById(vehicleDto.getVehicleCategoryId()).orElse(null);

        if(vehicleCategory!=null){
            Vehicle newVehicle=new Vehicle();
            newVehicle.setNumber(vehicleDto.getNumber());
            newVehicle.setBrand(vehicleDto.getBrand());
            newVehicle.setVariant(vehicleDto.getVariant());
            newVehicle.setFuel(vehicleDto.getFuel());
            newVehicle.setRentPerDay(vehicleDto.getRentPerDay());
            newVehicle.setIsAvailable(vehicleDto.getIsAvailable());
            newVehicle.setVehicleCategory(vehicleCategory);

            return vehicleRepository.save(newVehicle);
        }else{
            return null;
        }
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehicle> getVehicleByCategory(Long id) {
        return vehicleRepository.findVehicleByCategory(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public UpdateResponse<Vehicle> updateVehicle(Long id, VehicleDto vehicleDto) {

        //checking if a vehicle is existing for the corresponding id
        Vehicle existingVehicle=vehicleRepository.findById(id).orElse(null);

        //checking if a vehicle category existing for the new category id
        VehicleCategory vehicleCategory=vehicleCategoryRepository.findById(vehicleDto.getVehicleCategoryId()).orElse(null);

        //create a new update response
        UpdateResponse<Vehicle> response=new UpdateResponse<>();

        if(existingVehicle!=null){
            if(vehicleCategory!=null){
                existingVehicle.setNumber(vehicleDto.getNumber());
                existingVehicle.setBrand(vehicleDto.getBrand());
                existingVehicle.setVariant(vehicleDto.getVariant());
                existingVehicle.setFuel(vehicleDto.getFuel());
                existingVehicle.setRentPerDay(vehicleDto.getRentPerDay());
                existingVehicle.setIsAvailable(vehicleDto.getIsAvailable());
                existingVehicle.setVehicleCategory(vehicleCategory);

                vehicleRepository.save(existingVehicle);

                response.setResponseMessage("Vehicle Updated Successfully");
                response.setUpdatedData(existingVehicle);
            }else{
                response.setResponseMessage("No Vehicle Category Found");
            }
        }else{
            response.setResponseMessage("No Vehicle Found");
        }

        return response;
    }

    @Override
    public String deleteVehicle(Long id) {

        //checking if a vehicle is existing for the given id
        boolean isExist= vehicleRepository.existsById(id);

        if(isExist){
            vehicleRepository.deleteById(id);

            return "Vehicle was deleted successfully";
        }else {
            return "Vehicle was not found";
        }
    }

}
