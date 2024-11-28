package com.demo.carrent.controller;

import com.demo.carrent.dto.RentDto;
import com.demo.carrent.entity.Rent;
import com.demo.carrent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RentController {

    private final RentService rentService;

    @Autowired
    public RentController(@Qualifier("rentServiceImpl") RentService rentService){
        this.rentService=rentService;
    }

    @PostMapping("/rents")
    public ResponseEntity<?> createRent(@RequestBody RentDto rentDto){
        try {
            Rent newRent= rentService.createRent(rentDto);

            return ResponseEntity.status(HttpStatus.OK).body(newRent);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
