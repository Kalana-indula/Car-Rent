package com.demo.carrent.service;

import com.demo.carrent.dto.RentDto;
import com.demo.carrent.entity.Rent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentService {

    //create new rent
    Rent createRent(RentDto rentDto);

    //get all rents
    List<Rent> getAllRents();


}
