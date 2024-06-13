package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //상품 저장
    public void save(Item item) {
        if (item.getId() == null) { //item을 처음에 저장할 때 id가 없을 때
            em.persist(item);
        } else { //item이 id를 가지고 있다? 이미 저장된 item이다.
            em.merge(item); //merge: update와 비슷한걸로 이해
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i  from Item", Item.class)
                .getResultList();
    }
}
