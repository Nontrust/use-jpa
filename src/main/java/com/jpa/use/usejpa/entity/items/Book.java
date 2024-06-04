package com.jpa.use.usejpa.entity.items;

import com.jpa.use.usejpa.entity.Item;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Book extends Item {
    private String author;
    private UUID isbn;
}
