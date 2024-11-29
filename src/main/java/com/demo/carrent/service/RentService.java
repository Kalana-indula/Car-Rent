package com.demo.carrent.service;

import com.demo.carrent.dto.RentDto;
import com.demo.carrent.dto.RentUpdateDto;
import com.demo.carrent.dto.response.CreateResponse;
import com.demo.carrent.dto.response.DeleteResponse;
import com.demo.carrent.dto.response.UpdateResponse;
import com.demo.carrent.entity.Rent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentService {

    //create new rent
    CreateResponse<Rent> createRent(RentDto rentDto);

    //get all rents
    List<Rent> getAllRents();

    //find rent by id
    Rent getRentById(Long id);

    //update rent
    UpdateResponse<Rent> updateRent(Long id, RentUpdateDto rentUpdateDto);

    //delete rent
    DeleteResponse deleteRent(Long id);
}
