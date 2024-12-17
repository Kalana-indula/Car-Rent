package com.demo.carrent.exception;

public class InvalidDateFormatException extends RuntimeException{

    public InvalidDateFormatException(String message){
        //setting parameters for super class constructor
        super(message);
    }
}
