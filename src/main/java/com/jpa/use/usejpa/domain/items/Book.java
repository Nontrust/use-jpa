package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.exception.ItemException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import static com.jpa.use.usejpa.domain.enumerate.ItemDType.BOOK;
import static org.springframework.util.StringUtils.hasLength;

@SuperBuilder
@RequiredArgsConstructor
@DiscriminatorValue("Book")
@Entity
public class Book extends Item {
    private String author;
    private String isbn;

    protected Book(String name, Long price, Integer stockQuantity,  String author, String isbn){
        super(BOOK, name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public static Book create(String name , Long price, Integer stockQuantity, String author, String isbn) throws ItemException {
        BookBuilder bookBuilder = (BookBuilder) Item.createSpecificItemBuilder(Book.class, name, price, stockQuantity);
        return Book.create(bookBuilder, author, isbn);
    }

    public static Book create(ItemBuilder itemBuilder, String author, String etc) throws ItemException {
        Book.BookBuilder bookBuilder = (Book.BookBuilder) itemBuilder;
        return bookBuilder
                .author(author)
                .isbn(etc)
                .build();
    }

    public static Map<String, String> findField(Book book){
        return Map.of("field1", book.author, "field2", book.isbn);
    }

    public void setField(String field1,String field2) {
        this.author = hasLength(field1)
                ? field1 : this.author ;
        this.isbn = hasLength(field2)
                ? field2 : this.isbn;
    }
}
