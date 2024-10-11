package com.example.spring_practice.mvc.controller.dot;

import com.example.spring_practice.mvc.service.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {

    private Integer id;
    private String name;
    private Integer age;
    private String job;
    private String specialty;
    private String address;
    private String postcode;
    public static UserResponseDto from(User entity) {
        return new UserResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getJob(),
                entity.getSpecialty(),
                null,
                null
        );
    }
}
