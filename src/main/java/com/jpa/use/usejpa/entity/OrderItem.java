package com.jpa.use.usejpa.entity;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id @Column(name = "order_item_id") @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    private Long orderPrice;
    private Integer count;
}
