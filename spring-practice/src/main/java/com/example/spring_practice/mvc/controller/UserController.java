package com.example.spring_practice.mvc.controller;

import com.example.spring_practice.mvc.service.User;
import com.example.spring_practice.mvc.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceInterface AUserService;

    @GetMapping("/1/data")
    public User detailData() {
        User user = AUserService.findById(1);
        return user;
    }
}
