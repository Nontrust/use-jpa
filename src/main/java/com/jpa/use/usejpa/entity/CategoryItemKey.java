package com.jpa.use.usejpa.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;

@Embeddable
public class CategoryItemKey{
    @OneToOne
    private Category category;
    @OneToOne
    private Item item;
}
