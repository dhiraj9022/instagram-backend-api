package com.instagram.exception;

public class UnAuthException extends RuntimeException{
    public UnAuthException(String message){
        super(message);
    }
}
