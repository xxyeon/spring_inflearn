package com.example.core.member;

public class MemberServiceImpl implements MemberService {
    //가입을 하고 조회하려면 저장소가 필요(MemberRepository)
    private final MemberRepository memberRepository; //MemberRepository 인터페이스만 존재

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //생성자를 통해서 memoryMemberRepository에 뭐가 들어갈지 결정
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
