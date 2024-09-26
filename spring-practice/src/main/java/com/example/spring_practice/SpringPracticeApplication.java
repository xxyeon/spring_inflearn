package com.example.spring_practice;

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
			throw new UsernameException();
		} else if (!StringUtils.hasLength(password)) {
			throw new PasswordException();
		} else if (!username.equals("admin") && !password.equals("1234")) {
			throw new DatabaseException();
		} if (true/* 강제로 예외를 발생시키기 위해 if (true) 추가 - 그렇지않으면 '아래부터 실행되지 않는데 코드가 있다'는 빌드 오류 */) {
			throw new RuntimeException("Cannot connect to the Database Server");
		}
		log.info("Connected to database");
	}
	public static void main(String[] args) {
		try {
			connect(null, "9876");
			connect("user", null);
			connect("user", "9876");
			connect("admin", "1234");
		} catch(UsernameException e) {
			log.warn("유저네임 정보가 일치하지 않음");
		} catch(PasswordException e) {
			log.warn("패스워드 정보가 일치하지 않음");
		} catch(DatabaseException e) {
			log.warn("유저네임과 패스워드 불일치로 데이터베이스 접속 실패");
		} catch(RuntimeException e) {
			log.warn("유저 정보는 맞지만, 데이터베이스 연결 오류로 인해 연결 실패");
		}


	}

}
