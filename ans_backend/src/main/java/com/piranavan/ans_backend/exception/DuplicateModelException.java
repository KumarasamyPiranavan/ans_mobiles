package com.piranavan.ans_backend.exception;

public class DuplicateModelException extends RuntimeException{
    public DuplicateModelException(String message){
        super(message);
    }
}
