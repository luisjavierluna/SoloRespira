package com.SoloRespira.SoloRespira.controllers;

import com.SoloRespira.SoloRespira.dtos.ErrorDto;
import com.SoloRespira.SoloRespira.exceptions.GeneralException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorDto> notFoundException(GeneralException ex){
        ErrorDto errorDto = new ErrorDto(400, ex.getMessage());
        return  new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> invalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return errors;
    }
    
}
