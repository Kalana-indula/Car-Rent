package com.demo.carrent.service;

import com.demo.carrent.common.PaymentStatus;
import com.demo.carrent.common.RentStatus;
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

import java.math.BigDecimal;
import java.util.List;


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
        Rent rent=rentRepository.findById(paymentDto.getRentId()).orElse(null);

        CreateResponse<Payment> paymentCreateResponse=new CreateResponse<>();

        if(rent!=null){
            //check if the rent is approved
            if(rent.getRentStatus()==RentStatus.APPROVED){
                //create new payment
                Payment payment=new Payment();

                payment.setAmount(rent.getPrice());
                payment.setRent(rent);
                payment.setUser(rent.getUser());

                paymentRepository.save(payment);

                //set payment status in rent to 'PAID'
                rent.setPaymentStatus(PaymentStatus.PAID);
                rentRepository.save(rent);

                paymentCreateResponse.setStatusMessage("Payment create successfully");
                paymentCreateResponse.setCreatedData(payment);

                return paymentCreateResponse;
            }else{
                paymentCreateResponse.setStatusMessage("Rent is not approved");
                return paymentCreateResponse;
            }

        }else{
            paymentCreateResponse.setStatusMessage("No valid rent found");

            return paymentCreateResponse;
        }

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
