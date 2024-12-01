package com.demo.carrent.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LicenseDto {

    private String licenseNumber;
    private LocalDate expirationDate;
    private Long userId;
}
