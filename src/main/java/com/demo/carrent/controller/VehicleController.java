package com.demo.carrent.controller;

import com.demo.carrent.dto.VehicleDto;
import com.demo.carrent.dto.response.UpdateResponse;
import com.demo.carrent.entity.Vehicle;
import com.demo.carrent.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(@Qualifier("vehicleServiceImpl") VehicleService appVehicleService){
        this.vehicleService=appVehicleService;
    }

    @PostMapping("/vehicles")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDto vehicleDto){
        try{
            Vehicle newVehicle=vehicleService.createVehicle(vehicleDto);

            return ResponseEntity.status(HttpStatus.OK).body(newVehicle);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<?> findVehicleById(@PathVariable Long id){
        try {
            Vehicle existingVehicle=vehicleService.getVehicleById(id);

            if(existingVehicle!=null){
                return ResponseEntity.status(HttpStatus.FOUND).body(existingVehicle);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle Not Found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/vehicleCategories/{id}/vehicles")
    public ResponseEntity<?> findVehiclesByCategory(@PathVariable Long id){
        try {
            List<Vehicle> vehicles=vehicleService.getVehicleByCategory(id);

            if(!vehicles.isEmpty()){
                return ResponseEntity.status(HttpStatus.FOUND).body(vehicles);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Vehicle Found For The Category");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/vehicles")
    public ResponseEntity<?> findAllVehicles(){
        try {
            List<Vehicle> vehicles=vehicleService.getAllVehicles();

            if(!vehicles.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(vehicles);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Vehicles Found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<?> updateVehicles(@PathVariable Long id,@RequestBody VehicleDto vehicleDto){
        try {
            UpdateResponse<Vehicle> updatedStatus= vehicleService.updateVehicle(id,vehicleDto);

            //Checking updating status
            if(updatedStatus.getUpdatedData()!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedStatus.getUpdatedData());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedStatus.getResponseMessage());
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id){
        try {
            String status=vehicleService.deleteVehicle(id);

            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
