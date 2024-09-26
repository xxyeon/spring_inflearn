package com.example.spring_practice.exception;

public class PasswordException extends RuntimeException{

    public PasswordException() {
        super("패스워드 오류");
    }
}
