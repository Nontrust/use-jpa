package com.jpa.use.usejpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {
    @Id
    private Long id;
    @ManyToOne
    private Orders orders;
    @ManyToOne
    private Item items;

    private Long orderPrice;
    private Integer count;
}
