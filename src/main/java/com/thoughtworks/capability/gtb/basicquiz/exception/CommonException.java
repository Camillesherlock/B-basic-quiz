package com.thoughtworks.capability.gtb.basicquiz.exception;

public class CommonException extends Exception{
    private String message;
    public CommonException(){};
    public CommonException(String message){
        super(message);
        this.message = message;
    }
    public String getError() {
        return message;
    }
}
