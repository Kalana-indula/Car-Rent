package com.demo.carrent.dto;

import com.demo.carrent.common.PaymentStatus;
import com.demo.carrent.common.RentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class RentDto {

    private LocalDate startingDate;
    private LocalDate endDate;
    private Long userId;
    private Long vehicleId;
}
