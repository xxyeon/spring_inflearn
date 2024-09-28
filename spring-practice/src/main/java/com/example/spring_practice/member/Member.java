package com.example.spring_practice.member;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
public class Member {

    private Integer id;// Reference Type 주소를 담는 그릇 : 객체가 담기며, NULL 이 들어갈 수 있다.
    @Builder.Default
    private String name = "unNamed";
    private int age;// Primitive Type 값을 담는 그릇 : 값 그 자체가 담기며, NULL 이 들어갈 수 없다. (무조건 값이 있어야하기에 기본값 존재)

    @Builder.Default
    private String email = "undefined";

    @Builder
    public Member(int age, String email) {
        this.age = age;
        this.email = email;
    }



}
