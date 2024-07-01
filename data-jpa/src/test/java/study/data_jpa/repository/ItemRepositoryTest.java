package study.data_jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.data_jpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired ItemRepository itemRepository;

    @Test
    public void save() {

        Item item = new Item("A"); //만약 id를 직접 넣어주면 save의 persist가 호출이 안된다. _ save는 객체일때 null이면 새로운 객체로 판단하는데 Id를 직접할당하면, 지금 "A"로 식별자를 세팅하고 save를 호출하니까 merge를 해버린다.
        itemRepository.save(item);
    }

}