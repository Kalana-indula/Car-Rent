package com.demo.carrent.dto;

import com.demo.carrent.common.PaymentStatus;
import com.demo.carrent.common.RentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RentUpdateDto {
    private LocalDate startingDate;
    private LocalDate endDate;
    private RentStatus rentStatus;
    private PaymentStatus paymentStatus;
    private Long vehicleId;
}
