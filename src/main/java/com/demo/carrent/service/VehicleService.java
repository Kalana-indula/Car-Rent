package com.demo.carrent.service;

import com.demo.carrent.dto.VehicleDto;
import com.demo.carrent.dto.response.UpdateResponse;
import com.demo.carrent.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    //create a new vehicle
    Vehicle createVehicle(VehicleDto vehicleDto);

    //Get a vehicle by id
    Vehicle getVehicleById(Long id);

    //Get all existing vehicles
    List<Vehicle> getAllVehicles();

    //Update vehicle
    UpdateResponse<Vehicle> updateVehicle(Long id, VehicleDto vehicleDto);

    //delete a vehicle
    String deleteVehicle(Long id);
}
