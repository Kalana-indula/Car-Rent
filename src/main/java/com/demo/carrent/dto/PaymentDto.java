package com.demo.carrent.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PaymentDto {

    private BigDecimal amount;
    private Long rentId;
    private Long userId;
}
