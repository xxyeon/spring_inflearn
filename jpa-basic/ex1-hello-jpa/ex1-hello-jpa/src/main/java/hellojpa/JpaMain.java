package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);
            em.flush();
            em.clear();
            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.id = " + findMember.getUsername());

            //criteria

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close(); //애플리케이션이 완전히 종료되면 emf.close()

    }
}
