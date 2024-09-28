package com.example.spring_practice.member;

import lombok.Getter;

@Getter
public class MemberRequestDto {
    private String name;
    private String email;

    public MemberRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
