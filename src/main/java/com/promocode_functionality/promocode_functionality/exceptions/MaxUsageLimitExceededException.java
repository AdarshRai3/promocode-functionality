package com.promocode_functionality.promocode_functionality.exceptions;

public class MaxUsageLimitExceededException extends RuntimeException {
    public MaxUsageLimitExceededException(String message) {
        super(message);
    }
}
