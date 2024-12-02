package com.demo.carrent.service;

import com.demo.carrent.entity.User;
import com.demo.carrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    //Injecting 'UserRepository' instance using constructor injection
    @Autowired
    public UserServiceImpl(UserRepository userRepo){
        this.userRepository=userRepo;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByIdNumber(String idNo) {
        return userRepository.findUserByIdNo(idNo);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        //find if a correspondig user is existing for given id
        User existingUser=userRepository.findById(id).orElse(null);

        if(existingUser!=null){
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setIdNo(user.getIdNo());
            existingUser.setPhone(user.getPhone());
            existingUser.setEmail(user.getEmail());

            return userRepository.save(existingUser);
        }else{
            return null;
        }
    }

    @Override
    public String deleteUser(Long id) {

        //Check if the user is existing for given id
        boolean isExist=userRepository.existsById(id);

        if(isExist){
            userRepository.deleteById(id);

            return "User Deleted Successfully";
        }else{
            return "No User Found";
        }
    }
}
