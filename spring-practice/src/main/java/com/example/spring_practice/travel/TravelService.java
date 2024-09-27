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
            log.error("여행 패키지 예약 실패"); //근데 여기서 이렇게 로그를 찍는거 보다

            log.error(e.getMessage());
            //이렇게 로그를 출력하는게 내부 고객(개발자)에게 좋지않은가? 왜냐면 유저네임이 틀린건지, 비번이 틀린건지 알 수 있으니까

            //외부 고객(사용자) 에게 표시할 에러 (한번 감싸서 던짐)
            throw new CustomException(2, "RESERVATION_FAILED", "여행 패키지 예약 실패");
        } catch (RuntimeException e) {
            //내부 고객(개발자)에게 표기할 에러 메시지
            log.error("여행 패키지 예약 시 알 수 없는 에러 발생 - (2) 중간에 받아서 다시 넘기기", e);

            //외부 고객(사용자) 에게 표시할 에러
            throw new CustomException(0, "UNKNOWN_ERROR", "여행 패키지 예약 시 알 수 없는 에러 발생");
        }



        log.info("여행 패키지 예약 완료 (항공권 + 리조트 + 수영장)");
    }
}
