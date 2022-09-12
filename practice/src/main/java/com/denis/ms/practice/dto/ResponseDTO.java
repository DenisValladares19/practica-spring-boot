package com.denis.ms.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    public static final String CODE_OK = "00";
    public static final String CODE_FAIL = "99";
    public static final String MSG_OK = "Processed successfully";
    public static final String MSG_FAIL = "An error occurred while processing your request";

    private String code;
    private String message;
    private Object errors;
    private T result;
}
