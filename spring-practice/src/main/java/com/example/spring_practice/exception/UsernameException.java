package com.example.spring_practice.exception;

public class UsernameException extends RuntimeException{
    public UsernameException() {
        throw new RuntimeException("유저 네임 오류");
    }

}
