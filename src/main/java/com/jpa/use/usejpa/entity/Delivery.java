package com.jpa.use.usejpa.entity;

import jakarta.persistence.*;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class Delivery {
    @Id
    private Long id;
    private Address address;

    @Enumerated(STRING)
    private State state;

    @OneToOne(mappedBy = "delivery")
    private Order order;

}
