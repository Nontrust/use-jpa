package com.jpa.use.usejpa.entity.items;

import com.jpa.use.usejpa.entity.Item;
import jakarta.persistence.Entity;

@Entity
public class Act extends Item {
    private String artist;
    private String etc;
}
