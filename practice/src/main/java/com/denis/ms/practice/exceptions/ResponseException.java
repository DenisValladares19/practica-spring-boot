package com.denis.ms.practice.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ResponseException extends  RuntimeException {
    private String code;
    private String message;
    private Object errors;

    private HttpStatus status;

    public ResponseException(String message, String code, HttpStatus status, Object errors) {
        super(message);
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.status = status;
    }

    public ResponseException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public ResponseException(String message, HttpStatus status, String code) {
        super(message);
        this.message = message;
        this.status = status;
        this.code = code;
    }
}
