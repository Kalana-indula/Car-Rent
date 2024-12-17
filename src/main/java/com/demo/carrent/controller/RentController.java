package com.demo.carrent.controller;

import com.demo.carrent.dto.RentDto;
import com.demo.carrent.dto.RentStatusDto;
import com.demo.carrent.dto.RentUpdateDto;
import com.demo.carrent.dto.response.CreateResponse;
import com.demo.carrent.dto.response.DeleteResponse;
import com.demo.carrent.dto.response.UpdateResponse;
import com.demo.carrent.entity.Rent;
import com.demo.carrent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class RentController {

    private final RentService rentService;

    @Autowired
    public RentController(@Qualifier("rentServiceImpl") RentService rentService){
        this.rentService=rentService;
    }

    @PostMapping("/rents")
    public ResponseEntity<?> createRent(@RequestBody RentDto rentDto){
        try {
            CreateResponse<Rent> newRent= rentService.createRent(rentDto);

            if(newRent.getCreatedData()!=null){
                return ResponseEntity.status(HttpStatus.OK).body(newRent.getCreatedData());
            }else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(newRent.getStatusMessage());
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/rents")
    public ResponseEntity<?> getAllRents(){
        try {
            List<Rent> rents=rentService.getAllRents();

            if(!rents.isEmpty()){
                return ResponseEntity.status(HttpStatus.FOUND).body(rents);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rents found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/rents/{id}")
    public ResponseEntity<?> findRentById(@PathVariable Long id){
        try {
            Rent existingRent=rentService.getRentById(id);

            if(existingRent!=null){
                return ResponseEntity.status(HttpStatus.FOUND).body(existingRent);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rent found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/rents/{id}")
    public ResponseEntity<?> updateRent(@PathVariable Long id,@RequestBody RentUpdateDto rentUpdateDto){
        try {
            UpdateResponse<Rent> updateResponse= rentService.updateRent(id,rentUpdateDto);

            if(updateResponse.getUpdatedData()!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updateResponse.getUpdatedData());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updateResponse.getResponseMessage());
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/status/rents/{id}")
    public ResponseEntity<?> updateRentStatus(@PathVariable Long id,@RequestBody RentStatusDto rentStatusDto){
        try {
            UpdateResponse<Rent> updateResponse= rentService.updateRentStatus(id,rentStatusDto);

            if(updateResponse.getUpdatedData()!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updateResponse.getUpdatedData());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updateResponse.getResponseMessage());
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/rents/{id}")
    public ResponseEntity<?> deleteRent(@PathVariable Long id){
        try {
            DeleteResponse rentDeletionStatus= rentService.deleteRent(id);

            return ResponseEntity.status(HttpStatus.OK).body(rentDeletionStatus.getStatusMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
