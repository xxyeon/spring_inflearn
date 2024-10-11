package com.example.spring_practice.mvc.service;

import com.example.spring_practice.mvc.controller.dot.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserServiceInterface{
    private static final Map<Integer, User> users;

    static {
        users = new HashMap<>();
        users.put(1, new User(1, "Aaron", 10, "Developer", "Backend"));
        users.put(2, new User(2, "Baron", 20, "Developer", "Frontend"));
        users.put(3, new User(3, "Caron", 30, "Engineer", "DevOps/SRE"));
    }

    public UserResponseDto findById(Integer id) {
        return UserResponseDto.from(users.get(id));
    }

    public List<UserResponseDto> findAll() {
//        users.values().stream().map((user) -> UserResponseDto.from(user)).toList(); 아래와 같은 코드
        return users.values().stream().map(UserResponseDto::from).toList();
    }

    public UserResponseDto save(String name, Integer age, String job, String specialty) {
        int generatedId = users.size() + 1;
        User saved = users.put(generatedId, new User(generatedId, name, age, job, specialty));
        return UserResponseDto.from(saved);
    }
}
