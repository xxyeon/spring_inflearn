package com.example.spring_practice.exception;

public class CustomException extends RuntimeException{

    private int priority;
    private String type;

    public CustomException(int priority, String type, String message) {
        super(message);
        this.priority = priority;
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }
}
