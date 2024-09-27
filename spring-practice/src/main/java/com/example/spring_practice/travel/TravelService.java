package com.example.spring_practice.travel;

import com.example.spring_practice.exception.CustomException;
import com.example.spring_practice.exception.DatabaseException;
import com.example.spring_practice.exception.PasswordException;
import com.example.spring_practice.exception.UsernameException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TravelService {

    public void reservation() {

        try {
            Database connection = new Database(null, "9876");
            connection.save("항공권");
            connection.save("리조트");
            connection.save("수영장");
            log.info("여행 패키지 예약 완료(리조트 + 항공권");
        }catch (UsernameException | PasswordException | DatabaseException e) {
            /* 1) 내부 고객(개발자)에게 표기할 에러메세지 */
            log.error("여행 패키지 예약 실패 - (3) 중간에 받아서 처리하기");
        } catch (RuntimeException e) {
            //내부 고객(개발자)에게 표기할 에러 메시지
            log.error("여행 패키지 예약 시 알 수 없는 에러 발생 - (3) 중간에 받아서 처리하기", e);
        }



        log.info("여행 패키지 예약 완료 (항공권 + 리조트 + 수영장)");
    }
}
