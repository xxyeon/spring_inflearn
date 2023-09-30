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

            /*
            임베디트 타입
            값 타입과 불변 객체
            값 타입의 비교

            Address address = new Address("city", "street", "zipcode");

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);

            em.persist(member);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member.setUsername("member1");
            member.setHomeAddress(copyAddress);
            em.persist(member2);*/
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homecity", "street", "zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddresHistory().add(new AddressEntity("old1", "street1", "zipcode1"));
            member.getAddresHistory().add(new AddressEntity("old2", "street2", "zipcode2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===========START========");
            Member findMember = em.find(Member.class, member.getId());


            /**
             * 컬렉션 조회
             */
            List<AddressEntity> addresHistory = findMember.getAddresHistory();
            for (AddressEntity address : addresHistory) {
                System.out.println("address = " + address.getAddress().getCity());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoritFood : favoriteFoods) {
                System.out.println("favoritFood = " + favoritFood);
            }

            /**
             * 임베디드 타입 수정
             * homeCity -> newCity
             */
            Address a = findMember.getHomeAddress();
            //findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            /**
             * 컬렉션 수정
             * 치킨 -> 한식 타입이 String이므로 update안되고 아예 새롭게 값을 수정해줘야함
             * old1 -> newCity
             */
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddresHistory().remove(new AddressEntity("old1", "street1", "zipcode1")); //equals hashcode이용해서 값 찾아서 remove
            findMember.getAddresHistory().add(new AddressEntity("newCity", "street1", "zipcode1"));

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
