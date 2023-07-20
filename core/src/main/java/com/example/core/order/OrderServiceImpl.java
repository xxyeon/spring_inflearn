package com.example.core.order;

import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.member.Member;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy; //DIP 해결
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //주문생성 요청이오면 회원 정보 조회하고
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인 정책에 회원 정보 넘겨주기

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
