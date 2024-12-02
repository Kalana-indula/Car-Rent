package com.demo.carrent.controller;

import com.demo.carrent.dto.LicenseDto;
import com.demo.carrent.entity.License;
import com.demo.carrent.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LicenseController {

    private LicenseService licenseService;

    @Autowired
    public LicenseController(@Qualifier("licenseServiceImpl") LicenseService licenseService){
        this.licenseService=licenseService;
    }

    //'@ModelAttribute' is used when form-data is posted through api
    @PostMapping("/license")
    public ResponseEntity<?> addLicense(@RequestParam("file") MultipartFile file,@ModelAttribute LicenseDto licenseDto){
        try{
            License newLicense=licenseService.addLicenseDetails(file,licenseDto);

            if(newLicense!=null){
                return ResponseEntity.status(HttpStatus.OK).body(newLicense);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
            }
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/license")
    public ResponseEntity<?> findAllLicense(){
        try {
            List<License> licensesList=licenseService.getAllLicenseDetails();

            if(!licensesList.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(licensesList);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No license found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
