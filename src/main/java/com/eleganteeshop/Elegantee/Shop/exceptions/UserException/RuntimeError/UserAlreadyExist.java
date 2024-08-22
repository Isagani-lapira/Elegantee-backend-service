package com.eleganteeshop.Elegantee.Shop.exceptions.UserException.RuntimeError;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserAlreadyExist extends RuntimeException {

    private String message = "User Already Existing";
    private final HttpStatus status;

    public UserAlreadyExist() {
        super("User Already Existing");
        this.status = HttpStatus.NOT_ACCEPTABLE;
    }
    public UserAlreadyExist(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.NOT_ACCEPTABLE;
    }

    public UserAlreadyExist(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

}
