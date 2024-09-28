package com.example.spring_practice;

import com.example.spring_practice.dto.ParsedRequestDto;
import com.example.spring_practice.dto.RequestDto;
import com.example.spring_practice.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringPracticeApplication {
	
	public static void main(String[] args) {

		Member baron = Member.of("baron");
		System.out.println("baron = " + baron);


		//Member 가 엔티티라면, 디비에서 가져온 엔티티 데이터를 ParsedRequestDto 로 변환해서 외부 전당용 객체로 만들어 줘야할 때,
		Member memberEntity = new Member(1, "aaron", 20, "aaron@example.com");

		// 바로 아래 코드 - 호출 불가능 : Private 생성자이기 때문
//		ParsedRequestDto newlycreated1 = new ParsedRequestDto(memberEntity.getName(), memberEntity.getEmail());

		//정적 패토리 메서드를 이용해 엔티티를 dto객체로 반환
		ParsedRequestDto newlycreated = ParsedRequestDto.of(memberEntity);


	}

}
