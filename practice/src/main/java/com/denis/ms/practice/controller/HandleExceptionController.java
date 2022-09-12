package com.denis.ms.practice.controller;

import com.denis.ms.practice.dto.ResponseDTO;
import com.denis.ms.practice.exceptions.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleExceptionController {

    @ExceptionHandler(value = { ResponseException.class })
    public ResponseEntity<ResponseDTO<String>> handleCustomException(ResponseException ex) {
        ResponseDTO<String> res = new ResponseDTO<>();
        res.setCode(ResponseDTO.CODE_FAIL);
        res.setMessage(ex.getMessage());
        return new ResponseEntity<>(res, ex.getStatus());
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ResponseDTO<String>> handleGenericException(Exception ex) {
        ResponseDTO<String> res = new ResponseDTO<>();
        res.setCode(ResponseDTO.CODE_FAIL);
        res.setMessage(ResponseDTO.MSG_FAIL);
        res.setErrors(ex.getMessage());

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
