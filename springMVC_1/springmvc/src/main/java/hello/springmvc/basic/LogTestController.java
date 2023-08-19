package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    // @Slf4j 로 아래 코드 제거 가능, 롬복이 제공하는 애노테이션
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";


        System.out.println("name = " + name); //name = Spring

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name); //{}가 name으로 치환
        // 2023-08-16 16:24:13.386  INFO 14952 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  :  info log=Spring
        log.warn(" warn log={}", name);
        log.error("error log={}", name);
        return "ok";
    }
}
