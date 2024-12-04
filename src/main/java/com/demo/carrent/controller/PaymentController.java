package com.demo.carrent.controller;

import com.demo.carrent.dto.PaymentDto;
import com.demo.carrent.dto.response.CreateResponse;
import com.demo.carrent.entity.Payment;
import com.demo.carrent.service.PaymentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(@Qualifier("paymentServiceImpl") PaymentService paymentService){
        this.paymentService=paymentService;
    }

    //create APIs

    @PostMapping("/payments")
    public ResponseEntity<?> makePayment(@RequestBody PaymentDto paymentDto){
        try {
            CreateResponse<Payment> paymentCreateResponse=paymentService.createPayment(paymentDto);

            if(paymentCreateResponse.getCreatedData()!=null){
                System.out.println(paymentCreateResponse.getStatusMessage());

                return ResponseEntity.status(HttpStatus.OK).body(paymentCreateResponse.getCreatedData());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(paymentCreateResponse.getStatusMessage());
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
