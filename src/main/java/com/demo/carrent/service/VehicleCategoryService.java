package com.demo.carrent.service;

import com.demo.carrent.entity.VehicleCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleCategoryService {

    //create a new vehicle category
    VehicleCategory createVehicleCategory(VehicleCategory vehicleCategory);

    //get a vehicle category by Id
    VehicleCategory getVehicleCategoryById(Long id);

    //get all vehicle categories
    List<VehicleCategory> getAllVehicleCategories();

    //update an existing vehicle category
    VehicleCategory updateVehicleCategory(Long id,VehicleCategory vehicleCategory);

    //delete an existing vehicle category
    String deleteVehicleCategory(Long id);
}
