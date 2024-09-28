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

		String request = "AARON";

		Member.MemberBuilder memberBuilder = Member.builder()
				.age(10)
				.email("common@example.com");
		if(request.equals("AARON")) {
			memberBuilder.id(1).name("aaron");
		} else if(request.equals("BARON")) {
			memberBuilder.id(2).name("baron");
		}

		Member aaron = memberBuilder.build();


		System.out.println("aaron = " + aaron);




	}

}
