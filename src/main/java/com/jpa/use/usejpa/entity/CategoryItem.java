package com.jpa.use.usejpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class CategoryItem {
    @EmbeddedId
    private CategoryItemKey categoryItemKey;
}
