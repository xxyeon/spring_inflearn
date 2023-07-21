package com.example.core.beanfind;

import com.example.core.AppConfig;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        //MemberService가 MemberServiceImpl의 인스턴스인가를 검사
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        //MemberService가 MemberServiceImpl의 인스턴스인가를 검사
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);

        //MemberService가 MemberServiceImpl의 인스턴스인가를 검사
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    //실패 test
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        //ac. getBean("xxxx", MemberService.class)

        //오른쪽에 있는 로직을 실행했을 때 왼쪽에 있는 예외 (NoSuchBeanDefinitionException)가 터져야한다.
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class)); //NoSuchBeanDefinitionException 이예외가 터져야 성공한 것이다
    }
}
