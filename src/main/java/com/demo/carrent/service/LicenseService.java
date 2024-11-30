package com.demo.carrent.service;

import com.demo.carrent.dto.response.DeleteResponse;
import com.demo.carrent.entity.License;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LicenseService {

    //add license
    License addLicenseDetails(License license);

    //get all license details
    List<License> getAllLicenseDetails();

    //Delete license details
    DeleteResponse deleteLicenseDetails(Long id);
}
