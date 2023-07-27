package com.example.core.web;

import com.example.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException{
        String requestURL = request.getRequestURI().toString();

        //MyLogger의 가짜 프록시 클래스를 만들어두고 http request와 상관 없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다.
        System.out.println("myLogger = " + myLogger.getClass()); //스프링이 조작해서 만든 애가 스프링 빈으로 등록된다.
        //myLogger의 기능을 실제 호출하는 시점에서 진짜를 찾아서 동작
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
