package com.jpa.use.usejpa.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = SINGLE_TABLE)
public abstract class Item {
    @Id @Column(name="item_id")
    private Long id;
    private String name;
    private Long price;
    private Integer stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
