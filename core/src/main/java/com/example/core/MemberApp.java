package com.example.core;

import com.example.core.member.Grade;
import com.example.core.member.Member;
import com.example.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //회원가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //회원 가입 확인
        Member findMember = memberService.findMember(1L);
        System.out.println("find Member = " + findMember.getName());
        System.out.println("new Member = " + member.getName());
    }
}
