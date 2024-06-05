package com.jpa.use.usejpa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(STRING)
    private State status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
}

