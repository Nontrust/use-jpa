package com.jpa.use.usejpa.entity;

import jakarta.persistence.*;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class Delivery {
    @Id
    private Long id;
    private Adress adress;
    @Enumerated(STRING)
    private State state;

}
