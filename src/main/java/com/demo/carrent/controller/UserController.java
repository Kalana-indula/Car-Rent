package com.demo.carrent.controller;

import com.demo.carrent.entity.User;
import com.demo.carrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserService appUserService){
        this.userService=appUserService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            User newUser=userService.createUser(user);

            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        try {
            User existingUser=userService.getUserById(id);

            if(existingUser!=null){
                return ResponseEntity.status(HttpStatus.FOUND).body(existingUser);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers(){
        try{
            List<User> allUsers=userService.getAllUsers();

            if(!allUsers.isEmpty()){
                return ResponseEntity.status(HttpStatus.FOUND).body(allUsers);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user){
        try {
            User updatedUser=userService.updateUser(id,user);

            if(updatedUser!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            String status=userService.deleteUser(id);

            return ResponseEntity.status(HttpStatus.OK).body(status);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
