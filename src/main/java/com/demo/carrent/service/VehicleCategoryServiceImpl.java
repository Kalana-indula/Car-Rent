package com.demo.carrent.service;

import com.demo.carrent.entity.VehicleCategory;
import com.demo.carrent.repository.VehicleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCategoryServiceImpl implements VehicleCategoryService{


    private VehicleCategoryRepository vehicleCategoryRepository;

//    Injecting vehicleRepository instance using constructor injection
    @Autowired
    public VehicleCategoryServiceImpl(VehicleCategoryRepository categoryRepository){
        this.vehicleCategoryRepository=categoryRepository;
    }

    @Override
    public VehicleCategory createVehicleCategory(VehicleCategory vehicleCategory) {
        return vehicleCategoryRepository.save(vehicleCategory);
    }

    @Override
    public VehicleCategory getVehicleCategoryById(Long id) {
        return vehicleCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<VehicleCategory> getAllVehicleCategories() {
        return vehicleCategoryRepository.findAll();
    }

    @Override
    public VehicleCategory updateVehicleCategory(Long id, VehicleCategory vehicleCategory) {

        //check if the vehicle category is existing
        VehicleCategory existingCategory=vehicleCategoryRepository.findById(id).orElse(null);

        if(existingCategory!=null){
            existingCategory.setCategoryName(vehicleCategory.getCategoryName());
            vehicleCategoryRepository.save(existingCategory);

            return existingCategory;
        }else{
            return null;
        }

    }

    @Override
    public String deleteVehicleCategory(Long id) {

        //finding if a category is existing for the given id
        Boolean isExist=vehicleCategoryRepository.existsById(id);

        if(isExist){
            vehicleCategoryRepository.deleteById(id);

            return "Vehicle Category Successfully Deleted";
        }else{
            return "No vehicle category found";
        }
    }
}
