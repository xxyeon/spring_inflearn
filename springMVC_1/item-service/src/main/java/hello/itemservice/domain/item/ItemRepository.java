package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //Component 스캔의 대상
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // 실제로는 HashMap 사용하면 안되고 static으로 사용함을 주의
    private static long sequence = 0L;

    // 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // 조회
    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 아이템 아이디를 넣고 아이템과 관련된 업데이트 파라미터를 넣으면 업데이트 되는 시스템
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        // 별도의 updataParam의 겍체를 만드는 것이 맞다. id가 사용이 안되었기 때문에
    }

    public void clearStore() {
        store.clear(); //HashMap 데이터 없애기
    }
}
