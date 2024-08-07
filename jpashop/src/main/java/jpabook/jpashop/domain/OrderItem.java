package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.jpashop.domain.item.Item;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.swing.plaf.BorderUIResource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자 protected로 지정
//@BatchSize(size = 100)
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private int orderPrice;
    private int count; //주문 수량

    //==비즈니스 로직==//
    public void cancel() {
        getItem().addStock(count); //주문 취소하면 주문한 재고 수량 번복
    }

    //==조회 로직==//
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); //주문하면 재고 수 감소
        return orderItem;
    }
}
