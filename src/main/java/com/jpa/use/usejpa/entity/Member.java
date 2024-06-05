package com.jpa.use.usejpa.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    @Embedded
    private Address address;
}
