package com.jpa.use.usejpa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class Orders {
    @Id
    private Long id;
    @ManyToOne
    private Member member;
    @OneToOne
    private Delivery delivery;

    private LocalDateTime orderDate;
    @Enumerated(STRING)
    private State status;
}

