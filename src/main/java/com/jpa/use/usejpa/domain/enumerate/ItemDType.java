package com.jpa.use.usejpa.domain.enumerate;

import com.jpa.use.usejpa.domain.items.Act;
import com.jpa.use.usejpa.domain.items.Book;
import com.jpa.use.usejpa.domain.items.Item;
import com.jpa.use.usejpa.domain.items.Movie;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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


    public static boolean valid(Class<?> itemClass){
        return Arrays.stream(values())
                .anyMatch(type -> type.getDType().equals(itemClass));
    }

    public boolean isValidClazz(Class<?> clazz) {
        return this.DType.equals(clazz);
    }

    public static Set<String> getDTypes() {
        return Arrays.stream(ItemDType.values())
                .map(i->i.DType)
                .map(ItemDType::getDTypes)
                .map(i->i.getClass().getSimpleName())
                .collect(Collectors.toSet());
    }

    public static Optional<ItemDType> getDTypes(Class<?> itemClass) {
        return Arrays.stream(values())
                .filter(itemDType -> itemDType.isValidClazz(itemClass))
                .findFirst();
    }


}
