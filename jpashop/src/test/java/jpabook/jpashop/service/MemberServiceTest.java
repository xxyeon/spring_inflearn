package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //test케이스이면 rollback이 적용된다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false) //rollback을 적용하지 않겠다는,,
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long saveId = memberService.join(member);

        //then
//        em.flush(); // 테스트 데이터 db에 반영되는것을 보고 싶으면,,,(insert 쿼리가 날라가는 걸 보고 싶으면) 디폴트로 테스트 데이터는 rollback을 해준다. insert 쿼리가 날라가지 않는다.
        //어차피 rollback이 된다. -> @Rollback(false)로 rollback을 막을 수 있다.
        assertEquals(member, memberRepository.findOne(saveId));
        //rollback을 하면 db에 insert쿼리를 보낼 이유가없으 -> 영속성 컨텍스트가 flush 하지 않는다.
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야함.

        //then
        fail("예외가 발생해야 한다."); //이게 발생하면 test가 실패했다는 것. 예외가 발생해서 이게 실행이 되면 안됨.
        
    }

    //test할때 외부 db를 사용한다는 점, db설치하고 아주 귀찮음.!!!! test는 완전히 격리된 환경으로 하는 방법이 메모리 db를 사용하는 것
    //운영로직은 main에 있는 resources가 우선권을 가짐. test는 test에 있는 resource가 있으면 그 것이  우선권을 가짐.
}