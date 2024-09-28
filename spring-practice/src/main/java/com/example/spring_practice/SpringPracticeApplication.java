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

import java.util.List;

@SpringBootApplication
@Slf4j
public class SpringPracticeApplication {
	
	public static void main(String[] args) {


		Member aaron = Member.builder()
				.age(10)
				.favorites(List.of("Game", "Animation"))
				.email("common@example.com")
				.favorite("book")
				.favorite("Cook")
				.build();

		Member baron = Member.builder()
				.age(10)
				.email("common@example.com")
				.favorite("book")
				.favorite("Cook")
				.build();


		System.out.println("aaron = " + aaron);
		System.out.println("baron = " + baron);




	}

}
