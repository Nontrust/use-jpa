package com.jpa.use.usejpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Category {
    @Id
    private Long id;
    private String name;
    @OneToOne
    private Category parentId;
}
