package com.demo.carrent.service;

import com.demo.carrent.dto.LicenseDto;
import com.demo.carrent.dto.response.DeleteResponse;
import com.demo.carrent.entity.License;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface LicenseService {

    //add license
    License addLicenseDetails(MultipartFile file, LicenseDto licenseDto) throws IOException;

    //find license by id
    License getLicenseByUser(String userIdNo);

    //get all license details
    List<License> getAllLicenseDetails();

    //Delete license details
    DeleteResponse deleteLicenseDetails(Long id);


}
