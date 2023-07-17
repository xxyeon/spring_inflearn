package com.example.core.discount;

import com.example.core.member.Grade;
import com.example.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int dicountFixAmount = 1000; //1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return dicountFixAmount; //vip이면 1000원 할인
        } else {
            return 0;
        }
    }
}
