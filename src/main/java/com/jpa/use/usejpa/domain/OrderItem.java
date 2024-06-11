package com.jpa.use.usejpa.domain;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class OrderItem {
    @Id @Column(name = "order_item_id") @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    private Long orderPrice;
    private Integer count;

    protected void setOrder(Order order) {
        this.order = order;
    }
    public void cancel(){
        getItem().addStockQuantity(count);
    }

    public Long getTotalPrice(){
        return this.orderPrice * count;
    }
}
