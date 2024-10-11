package com.example.spring_practice.mvc.controller;

import com.example.spring_practice.mvc.controller.dot.UserResponseDto;
import com.example.spring_practice.mvc.service.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateRequestDto {
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
