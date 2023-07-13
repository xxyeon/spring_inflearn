package com.example.core.member;

public class MemberServiceImpl implements MemberService {
    //가입을 하고 조회하려면 저장소가 필요(MemberRepository)
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
