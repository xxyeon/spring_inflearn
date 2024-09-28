package com.example.spring_practice;

import com.example.spring_practice.exception.CustomException;
import com.example.spring_practice.exception.DatabaseException;
import com.example.spring_practice.exception.PasswordException;
import com.example.spring_practice.exception.UsernameException;
import com.example.spring_practice.member.Member;
import com.example.spring_practice.member.MemberRequestDto;
import com.example.spring_practice.travel.TravelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
@Slf4j
public class SpringPracticeApplication {
	
	public static void main(String[] args) {

		MemberRequestDto dto = new MemberRequestDto("Aaron", "aaron@example.com");
		Member aaron = new Member(dto);

		aaron.setEmail("aaron@example.com");
		System.out.println("aaron = " + aaron);




	}

}
