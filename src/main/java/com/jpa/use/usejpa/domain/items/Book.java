package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.exception.ItemException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@RequiredArgsConstructor
@DiscriminatorValue("book")
@Entity
public class Book extends Item {
    private String author;
    private UUID isbn;

    protected Book(String name, Long price, Integer stockQuantity,  String author, UUID isbn){
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public static Book create(String name , Long price, Integer stockQuantity, String author, UUID isbn) throws ItemException {
        BookBuilder itemBuilder = (BookBuilder) Item.createSpecificItemBuilder(Book.class, name, price, stockQuantity);
        return itemBuilder
                .author(author)
                .isbn(isbn)
                .build();
    }
}
