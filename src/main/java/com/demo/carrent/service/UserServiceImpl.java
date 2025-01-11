package com.demo.carrent.service;

import com.demo.carrent.entity.User;
import com.demo.carrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final EmailService emailService;

    //Injecting 'UserRepository' instance using constructor injection
    //Injecting 'EmailService' instance using constructor injection

    @Autowired
    public UserServiceImpl(UserRepository userRepo,EmailService emailService){
        this.userRepository=userRepo;
        this.emailService=emailService;
    }

    @Override
    public User createUser(User user) {

        User savedUser=userRepository.save(user);

        //send an email to the user
        emailService.sendEmail(
                savedUser.getEmail(),
                "Your registration successfull",
                "Dear " + savedUser.getFirstName() + ",\n\nThank you for registering with us.\n\nBest regards,\nCar Rent Service Team"
        );
        return savedUser;
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
