package com.example.spring_practice.member;

public class Member {

    private Integer id;     // Reference Type 주소를 담는 그릇 : 객체가 담기며, NULL 이 들어갈 수 있다.
    private String name;
    private int age;        // Primitive Type 값을 담는 그릇 : 값 그 자체가 담기며, NULL 이 들어갈 수 없다. (무조건 값이 있어야하기에 기본값 존재)
    private String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("Member(id=%s, name=%s, age=%s, email=%s)\"", id, name, age, email);
    }
}
