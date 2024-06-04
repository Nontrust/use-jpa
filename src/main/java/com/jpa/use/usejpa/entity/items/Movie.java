package com.jpa.use.usejpa.entity.items;

import com.jpa.use.usejpa.entity.Item;
import jakarta.persistence.Entity;

@Entity
public class Movie extends Item {
    private String Director;
    private String actor;
}
