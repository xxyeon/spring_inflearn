package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor //final이 있는 필드만 가지고 생성자를 만들어준다.
@Service
//jpa에서 데이터 변경이나 로직은 트랜잭션안에서 실행되어야한다.
@Transactional(readOnly = true)
//스프링이 제공하는 트랜잭션과 javax인 자바에서 쓸 수 있는 트랜잭션이 있는데 스프링이 제공하는 트랜잭션을 쓰는제 다양한 옵션을 사용할 수 있다.
public class MemberService {


    private final MemberRepository memberRepository;
    //생성자 하나시 스프링이 자동으로 @Autowired 적용

    /**
     *
     *  회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member); //member를 persist하는 시점에서 db에 반영이 전이더라도 영속성 컨텍스트에 key값을 pk로 채워주고 객체의 id값에도 값을 채워준다.
        return member.getId(); //db에 반영 전이더라도 영속성 컨텍스트의 key 값에 값이 있다. 따라서 값을 가져올 수 있다.
    }

    //was가 동시에 여러개가 뜨고, memberA라는 이름으로 동시에 검증로직을 호출하게되면 문제가 된다. 멀티스레드 상황고려 해서 db에 member의 name을 unique제약조건을 걸어줘야 안전하다.
    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        //멤버수를 세가지고 0보다 크면 예외 터트리는게 더 최적화 될 것이다.
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    //단순히 읽기 같은 경우 트랜잿견은 readOnly를 true해서 최적화 할 수 있다.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
