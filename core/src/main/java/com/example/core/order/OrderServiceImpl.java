package com.example.core.order;

import com.example.core.discount.DiscountPolicy;
import com.example.core.member.Member;
import com.example.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    //final이면 무조건 할당이 되어야함.
    //DIP의존: 이전 코드와 비교해보면 인터페이스만 존재한다는 걸 알 수 있다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; //DIP 해결

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //주문생성 요청이오면 회원 정보 조회하고
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인 정책에 회원 정보 넘겨주기

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
