package com.jpa.use.usejpa.domain;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Entity
public class Delivery {
    @Id
    private Long id;
    private Address address;

    @Enumerated(STRING)
    private OrderStatus state;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    protected void setOrder(Order order){
        this.order = order;
    }

}
