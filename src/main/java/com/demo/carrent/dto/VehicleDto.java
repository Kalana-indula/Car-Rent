package com.demo.carrent.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleDto {

    private String number;
    private String brand;
    private String variant;
    private String fuel;
    private Double rentPerDay;
    private Boolean isAvailable;
    private Long vehicleCategoryId;
}
