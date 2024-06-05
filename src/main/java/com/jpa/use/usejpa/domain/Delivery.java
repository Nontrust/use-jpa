package com.jpa.use.usejpa.domain;

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

    public void setOrder(Order order){
        this.order = order;
    }

}
