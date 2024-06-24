package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.RestController;
import study.data_jpa.entity.Member;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return em.createQuery("select m from Member m where m.username = :username and m.age > :age")
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    //멤버 엔티티에 @NamedQuery를 불러올 수 있다.
    public List<Member> findByUsername(String username) {
        return em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("username", username)
                .getResultList();

    }

    //순수 jpa 페이징과 정렬
    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Member m where m.age =: age order by m.username desc")
                .setParameter("age", age)
                .setFirstResult(offset) //몇번째부터
                .setMaxResults(limit) //어디까지 가져오겠다.
                .getResultList();
    }

    //페이징을 할 때 몇번째 페이지인지 알려주기 위한 함수
    public long totalCount(int age) {
        return em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    /**
     * JPA를 사용한 벌크성 수정 쿼리
     */
    public int bulkAgePlus(int age) {
        int resultCount = em.createQuery(
                "update Member m set m.age = m.age + 1" +
                        " where  m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
        return resultCount;

    }
}
