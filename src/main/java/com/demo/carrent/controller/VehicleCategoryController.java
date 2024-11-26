package com.demo.carrent.controller;

import com.demo.carrent.entity.VehicleCategory;
import com.demo.carrent.service.VehicleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleCategoryController {

    private VehicleCategoryService vehicleCategoryService;

    @Autowired
    public VehicleCategoryController(@Qualifier("vehicleCategoryServiceImpl") VehicleCategoryService categoryService){
        this.vehicleCategoryService=categoryService;
    }

    //create new vehicle category
    @PostMapping("/vehicleCategories")
    public ResponseEntity<?> createVehicleCategory(@RequestBody VehicleCategory vehicleCategory){
        try{
            VehicleCategory newCategory= vehicleCategoryService.createVehicleCategory(vehicleCategory);
            return ResponseEntity.status(HttpStatus.OK).body(newCategory);
        }catch (Exception e){
            //catch the cause of error if there are any
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/vehicleCategories")
    public ResponseEntity<?> getAllVehicleCategories(){
        try{
            List<VehicleCategory> vehicleCategoryList=vehicleCategoryService.getAllVehicleCategories();

            //Check if there aren't any category available
            if(!vehicleCategoryList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(vehicleCategoryList);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Category List Found");
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/vehicleCategories/{id}")
    public ResponseEntity<?> updateVehicleCategory(@PathVariable Long id,@RequestBody VehicleCategory vehicleCategory){
        try{
            VehicleCategory updatedCategory=vehicleCategoryService.updateVehicleCategory(id,vehicleCategory);

            if(updatedCategory!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No category found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/vehicleCategories/{id}")
    public ResponseEntity<?> deleteVehicleCategory(@PathVariable Long id){
        try{
            String status= vehicleCategoryService.deleteVehicleCategory(id);

            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
