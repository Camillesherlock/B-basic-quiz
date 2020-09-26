package com.thoughtworks.capability.gtb.basicquiz.exception;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String message) {
        super(message);
    }

}