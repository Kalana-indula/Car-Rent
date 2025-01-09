package com.demo.carrent.service;

import com.demo.carrent.dto.RentDto;
import com.demo.carrent.dto.RentStatusDto;
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

    //update rent status
    UpdateResponse<Rent> updateRentStatus(Long id, RentStatusDto rentStatusDto);

    //complete rent
    UpdateResponse<Rent> completeRent(Long id,RentStatusDto rentStatusDto);

    //delete rent
    DeleteResponse deleteRent(Long id);
}
