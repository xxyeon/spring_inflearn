package com.example.spring_practice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class SpringPracticeApplication {
	
	public static void main(String[] args) {

		String type = "LOVE";
		MessageType messageType = MessageType.findByName(type);
        log.info("당신에게 메세지를 보냅니다. : " + messageType.getMessage());
        log.info("다음으로부터 전송되었습니다. : " + messageType.getSendMediaType());
    }


}
