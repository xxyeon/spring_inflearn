package com.example.spring_practice.member;

import lombok.*;

import java.util.List;

@Builder
@ToString
@AllArgsConstructor
public class Member {

    private Integer id;// Reference Type 주소를 담는 그릇 : 객체가 담기며, NULL 이 들어갈 수 있다.
    @Builder.Default
    @Getter
    private String name = "unNamed";
    private int age;// Primitive Type 값을 담는 그릇 : 값 그 자체가 담기며, NULL 이 들어갈 수 없다. (무조건 값이 있어야하기에 기본값 존재)

    @Builder.Default
    @Getter
    private String email = "undefined";


    public static class MemberBuilder {
        public MemberBuilder age(int age) {
            if (age >= 30) {
                throw new RuntimeException("30세 이상은 설정 불가능합니다.");
            }
            this.age = age;
            return this;
        }
    }


    public static Member of(String name) {
        //factory
        return new Member(1, name, 0, "undefined");
    }

}
