package com.example.spring_practice;

import com.example.spring_practice.exception.CustomException;
import com.example.spring_practice.exception.DatabaseException;
import com.example.spring_practice.exception.PasswordException;
import com.example.spring_practice.exception.UsernameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
@Slf4j
public class SpringPracticeApplication {


	static void connect(String username, String password) {
		if (!StringUtils.hasLength(username)){
			throw new CustomException(1, "EMPTY_USERNAME", "유저네임 입력값은 필수 입니다.");
		} else if (!StringUtils.hasLength(password)) {
			throw new CustomException(2, "EMPTY_PASSWORD", "password 입력값은 필수 입니다.");
		} else if (!username.equals("admin") && !password.equals("1234")) {
			throw new CustomException(3, "LOGIN_FAILE", "로그인 정보 불일치");
		} if (true/* 강제로 예외를 발생시키기 위해 if (true) 추가 - 그렇지않으면 '아래부터 실행되지 않는데 코드가 있다'는 빌드 오류 */) {
			throw new CustomException(4,"CONNECT_ERROR", "데이터베이스 연결 실패");
		}
		log.info("Connected to database");
	}
	public static void main(String[] args) {
		try {
			connect(null, "9876");
			connect("user", null);
			connect("user", "9876");
			connect("admin", "1234");
		} catch(CustomException e) {
			log.error(String.format("[%s] (Priority: %s) %s", e.getType(), e.getPriority(), e.getMessage()));
		}


	}

}
