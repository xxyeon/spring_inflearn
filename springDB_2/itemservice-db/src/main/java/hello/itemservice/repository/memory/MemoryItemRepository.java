package hello.itemservice.repository.memory;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryItemRepository implements ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = findById(itemId).orElseThrow();
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();
        return store.values().stream() //itemName과 maxPrice가 store에 있는지 검사
                .filter(item -> {
                    if (ObjectUtils.isEmpty(itemName)) { //itemName이 없으면 검색 조건을 사용하지 않는 것이다.
                        return true;
                    }
                    return item.getItemName().contains(itemName); //itemName이 있으면 검색 조건을 사용하는 것이므로 store에서 검색조건(itemName)과 같은 것만 return
                }).filter(item -> {
                    if (maxPrice == null) {
                        return true;
                    }
                    return item.getPrice() <= maxPrice;
                })
                .collect(Collectors.toList()); //조건에 맞는것을 모아서 list로 반환
    }

    public void clearStore() {
        store.clear();
    } //test를 할 때마다 중복 데이터가 있으므로 데이터를 지워주는 코드 test에서 사용하기 위한 메서드

}
