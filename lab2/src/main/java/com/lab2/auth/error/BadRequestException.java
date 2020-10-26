package com.lab2.auth.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super(String.format("Bad request error: check if you've entered the URL correctly"));
    }
}
