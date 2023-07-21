package com.example.core;

import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        //스프링은 모든게 ApplicationContext로 시작 (컨테이너라고 보면된다. 이게 bean을 관리해준다.)
        //AppConfig에 있는 환경설정 정보를 가지고 스프링이 Bean들을 컨테이너에 넣어서 관리해준다.
        //이전에 appConfig에서 직접 찾아왔는데 이제는 스프링 컨테이너를 통해서 찾는다.
        //Bean이름은 메서드 이름으로 등록된다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //회원가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //회원 가입 확인
        Member findMember = memberService.findMember(1L);
        System.out.println("find Member = " + findMember.getName());
        System.out.println("new Member = " + member.getName());
    }
}
