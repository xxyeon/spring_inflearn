package com.example.spring_practice.dto;

import com.example.spring_practice.member.Member;

public class ParsedRequestDto {

        private final String name;
        private final String email;

    private ParsedRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static ParsedRequestDto of(Member member) {

        ParsedRequestDto created = new ParsedRequestDto(
                member.getName(),
                member.getEmail()
        );
        return created;
    }
}
