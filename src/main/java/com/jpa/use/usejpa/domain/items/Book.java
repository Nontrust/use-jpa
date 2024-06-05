package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.domain.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
@DiscriminatorValue("book")
public class Book extends Item {
    private String author;
    private UUID isbn;
}
