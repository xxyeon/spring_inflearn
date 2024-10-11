package com.example.spring_practice.mvc.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateRequestDto {
    @NotBlank
    private String name;
    @Min(10)
    private Integer age;
    @NotNull
    private String job;
    private String specialty = "(empty)";
}
