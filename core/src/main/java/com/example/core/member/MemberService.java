package com.example.core.member;

public interface MemberService {
    //회원 가입, 회원 조회 두가지 서비스 제공

    void join(Member member);
    Member findMember(Long memberId);
}
