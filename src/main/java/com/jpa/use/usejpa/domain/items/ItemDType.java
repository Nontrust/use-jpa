package com.jpa.use.usejpa.domain.items;

import lombok.Getter;

@Getter
public enum ItemDType {
    ITEM(Item.class),
    BOOK(Book.class),
    MOVIE(Movie.class),
    ACT(Act.class);

    private final Class<? extends Item> DType;

    ItemDType(Class<? extends Item> itemClass) {
        this.DType = itemClass;
    }

}
