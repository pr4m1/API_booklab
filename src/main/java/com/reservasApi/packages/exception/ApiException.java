package com.reservasApi.packages.exception;

public class ApiException  extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
