package com.example.core;

import com.example.core.discount.FixDiscountPolicy;
import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import com.example.core.member.MemoryMemberRepository;
import com.example.core.order.OrderService;
import com.example.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
        //MemberServiceImpl은 MemoryMemberRepository를 사용한다고 결정
    }

    public OrderService orderService() { //orderService에 대한 구체적인 할인 정책을 여기서 선택
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
