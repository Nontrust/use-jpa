package com.jpa.use.usejpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;

import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Item {
    @Id
    private Long id;
    private String name;
    private Long price;
    private Integer stockQuantity;
}
