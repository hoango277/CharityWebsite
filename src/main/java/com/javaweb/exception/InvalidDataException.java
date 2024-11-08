package com.javaweb.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}