package hu.OpenFishBackend.controller;

import hu.OpenFishBackend.Exceptions.EmailAlreadyExists;
import hu.OpenFishBackend.Exceptions.UsernameAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<Object> usernameAlreadyExists(UsernameAlreadyExists ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.FOUND.value());
        errorBody.put("error", ex.getMessage());
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<Object> emailAlreadyExists(EmailAlreadyExists ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.FOUND.value());
        errorBody.put("error", ex.getMessage());
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);

    }


}
