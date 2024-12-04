package com.demo.carrent.service;

import com.demo.carrent.dto.PaymentDto;
import com.demo.carrent.dto.response.CreateResponse;
import com.demo.carrent.dto.response.DeleteResponse;
import com.demo.carrent.entity.Payment;
import com.demo.carrent.entity.Rent;
import com.demo.carrent.entity.User;
import com.demo.carrent.repository.PaymentRepository;
import com.demo.carrent.repository.RentRepository;
import com.demo.carrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    private final UserRepository userRepository;

    private final RentRepository rentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository,UserRepository userRepository,RentRepository rentRepository){
        this.paymentRepository=paymentRepository;
        this.userRepository=userRepository;
        this.rentRepository=rentRepository;
    }

    @Override
    public CreateResponse<Payment> createPayment(PaymentDto paymentDto) {

        //find user and rent
        User user=userRepository.findById(paymentDto.getUserId()).orElse(null);

        Rent rent=rentRepository.findById(paymentDto.getRentId()).orElse(null);

        CreateResponse<Payment> paymentCreateResponse=new CreateResponse<>();

        if(rent!=null){
            if(user!=null){
                Payment payment=new Payment();

                payment.setAmount(paymentDto.getAmount());
                payment.setRent(rent);
            }
        }else{

        }


        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return List.of();
    }

    @Override
    public Payment updatePayment(PaymentDto paymentDto) {
        return null;
    }

    @Override
    public DeleteResponse deletePayment(Long id) {
        return null;
    }
}
