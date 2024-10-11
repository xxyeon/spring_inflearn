package com.example.spring_practice.mvc.service;

import com.example.spring_practice.mvc.controller.dot.UserResponseDto;

import java.util.List;

public interface UserServiceInterface {
    UserResponseDto findById(Integer id);

    List<UserResponseDto> findAll();

    UserResponseDto save(String name, Integer age, String job, String specialty);
}