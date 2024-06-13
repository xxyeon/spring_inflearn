package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor //스프링 부트 jpa를 사용하면 @PersistenceContext대신에 사용할수있음.
public class MemberRepository {


//    @PersistenceContext //엔티티 매니저 주입 spring boot starter jpa 라이브러리가 다 해줌
    //원래 엔티티 매니저는 @PersistenceContext라는 표준 어노테이션이 필요한데, 스프링부트에서 @Autowired로다 주입 가능하게 해준다.
    //스프링 부트 jpa가 아니면 지원하지 않음.
    private final EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

    }
}
