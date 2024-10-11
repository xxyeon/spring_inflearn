package com.example.spring_practice.mvc.service;

import java.util.List;

public interface UserServiceInterface {
    User findById(Integer id);

    List<User> findAll();

    User save(String name, Integer age, String job, String specialty);
}