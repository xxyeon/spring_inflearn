package com.example.spring_practice;

import com.example.spring_practice.exception.CustomException;
import com.example.spring_practice.exception.DatabaseException;
import com.example.spring_practice.exception.PasswordException;
import com.example.spring_practice.exception.UsernameException;
import com.example.spring_practice.member.Member;
import com.example.spring_practice.travel.TravelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
@Slf4j
public class SpringPracticeApplication {
	
	public static void main(String[] args) {
		Member aaron = new Member("Aaron", "aaron@example.com");
		Member baron = new Member("Baron", "baron@example.com");

		System.out.println("---");
		System.out.println(aaron);              // 객체
		System.out.println(aaron.toString());   // 객체
		System.out.println(aaron.getName());    // 필드 중 name
//      System.out.println(aaron.name);         // 필드 중 name (접근제어자 private)

		System.out.println("---");
		System.out.println(baron);              // 객체
		System.out.println(baron.toString());   // 객체
		System.out.println(baron.getName());    // 필드 중 name
//      System.out.println(baron.name);


	}

}
