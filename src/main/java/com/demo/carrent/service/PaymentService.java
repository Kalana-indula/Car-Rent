package com.demo.carrent.service;

import com.demo.carrent.dto.PaymentDto;
import com.demo.carrent.dto.response.CreateResponse;
import com.demo.carrent.dto.response.UpdateResponse;
import com.demo.carrent.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    //create a new payment
    CreateResponse<Payment> createPayment(PaymentDto paymentDto);

    //get all payments
    List<Payment> getAllPayments();

    //update payment
    UpdateResponse<Payment> updatePayment(Long rentId);

}
