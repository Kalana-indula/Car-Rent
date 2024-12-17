package com.demo.carrent.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Setter
@Getter
public class RentDto {

    private LocalDate startingDate;
    private LocalDate endDate;
    private Long userId;
    private Long vehicleId;
}
