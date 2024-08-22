package com.eleganteeshop.Elegantee.Shop.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
public class APIError {
    private String message;
    private HttpStatus status;
    private Timestamp timestamp;


    public APIError(String message, HttpStatus status, Timestamp timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
