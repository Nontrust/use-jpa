package com.jpa.use.usejpa.domain;

import com.jpa.use.usejpa.domain.items.Item;
import com.jpa.use.usejpa.exception.item.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderItem {
    @Id @Column(name = "order_item_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    private Long orderPrice;
    private Integer count;

    public static OrderItem createOrderItem(Item item, Integer count, Long orderPrice) throws NotEnoughStockException {
        return createOrderItem(null, item, count, orderPrice);
    }

    public static OrderItem createOrderItem(Order order, Item item, Integer count, Long orderPrice) throws NotEnoughStockException {
        item.removeStockQuantity(count);

        return OrderItem.builder()
                .order(order)
                .item(item)
                .count(count)
                .orderPrice(orderPrice)
                .build();
    }

    public void cancel() {
        getItem().addStockQuantity(count);
    }

    public Long getTotalPrice(){
        return this.orderPrice * count;
    }

    protected void setOrder(Order order) {
        this.order = order;
    }
    protected void setItem(Item  item) {
        this.item = item;
    }
}
