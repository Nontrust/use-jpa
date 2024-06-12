package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.exception.ItemException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@RequiredArgsConstructor
@Entity
@DiscriminatorValue("movie")
public class Movie extends Item {
    private String director;
    private String actor;

    protected Movie(String name, Long price, Integer stockQuantity,  String Director, String actor){
        super(name, price, stockQuantity);
        this.director = Director;
        this.actor = actor;
    }

    public static Movie create(String name , Long price, Integer stockQuantity, String director, String actor) throws ItemException {
        MovieBuilder itemBuilder = (MovieBuilder) Item.createSpecificItemBuilder(Movie.class, name, price, stockQuantity);
        return itemBuilder
                .director(director)
                .actor(actor)
                .build();
    }
}
