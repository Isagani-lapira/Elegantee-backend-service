package com.eleganteeshop.Elegantee.Shop.exceptions.UserException;

import com.eleganteeshop.Elegantee.Shop.exceptions.APIError;
import com.eleganteeshop.Elegantee.Shop.exceptions.UserException.RuntimeError.UserAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class UserExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserAlreadyExist.class})
    public final ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExist ex, WebRequest request){
        HttpStatus status = ex.getStatus();
        APIError apiError = new APIError(ex.getMessage(),status, Timestamp.from(Instant.now()));
        return ResponseEntity.status(status).body(apiError);
    }
}
