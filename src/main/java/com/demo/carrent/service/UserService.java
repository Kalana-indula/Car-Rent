package com.demo.carrent.service;

import com.demo.carrent.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //create a new user
    User createUser(User user);

    //find user by id
    User getUserById(Long id);

    //find user by id number
    User getUserByIdNumber(String idNo);

    //find all existing users
    List<User> getAllUsers();

    //update an existing user
    User updateUser(Long id,User user);

    //delete an existing user
    String deleteUser(Long id);
}
