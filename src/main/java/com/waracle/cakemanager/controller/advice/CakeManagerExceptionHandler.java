package com.waracle.cakemanager.controller.advice;

import com.waracle.cakemanager.exception.CakeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CakeManagerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CakeNotFoundException.class)
    public ResponseEntity<Object> handleCakeNotFoundException(
            CakeNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Cake does not exist");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
