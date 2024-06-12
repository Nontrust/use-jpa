package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.exception.ItemException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@DiscriminatorValue("act")
@RequiredArgsConstructor
@Entity
public class Act extends Item {
    private String artist;
    private String etc;

    protected Act(String name, Long price, Integer stockQuantity,  String artist, String etc){
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

    public static Act createMovie(String name , Long price, Integer stockQuantity, String artist, String etc) throws ItemException {
        ActBuilder itemBuilder = (ActBuilder) Item.createSpecificItemBuilder(Movie.class, name, price, stockQuantity);
        return itemBuilder
                .artist(artist)
                .etc(etc)
                .build();
    }

}
