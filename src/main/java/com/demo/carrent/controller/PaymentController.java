package com.demo.carrent.controller;

import com.demo.carrent.dto.PaymentDto;
import com.demo.carrent.dto.response.CreateResponse;
import com.demo.carrent.dto.response.UpdateResponse;
import com.demo.carrent.entity.Payment;
import com.demo.carrent.entity.Rent;
import com.demo.carrent.service.PaymentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/payments")
    public ResponseEntity<?> findAllPayments(){
        try {

            List<Payment> payments=paymentService.getAllPayments();

            if(!payments.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(payments);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payments found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id,@RequestBody PaymentDto paymentDto){
        try {
            UpdateResponse<Payment> paymentUpdateResponse= paymentService.updatePayment(id,paymentDto);

            if(paymentUpdateResponse.getUpdatedData()!=null){
                return ResponseEntity.status(HttpStatus.OK).body(paymentUpdateResponse.getUpdatedData());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(paymentUpdateResponse.getResponseMessage());
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
