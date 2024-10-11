package com.example.spring_practice.mvc.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String job;
    private String specialty;
}