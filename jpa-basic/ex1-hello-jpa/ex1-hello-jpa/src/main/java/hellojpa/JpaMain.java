package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //entityManager.close() 전에 code 작성

//            Member findMember = em.find(Member.class, 200L);
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();

            System.out.println("=====================");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close(); //애플리케이션이 완전히 종료되면 emf.close()

    }
}
