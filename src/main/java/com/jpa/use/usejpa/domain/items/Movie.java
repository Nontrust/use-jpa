package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.exception.ItemException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import static com.jpa.use.usejpa.domain.enumerate.ItemDType.MOVIE;
import static org.springframework.util.StringUtils.hasLength;

@SuperBuilder
@RequiredArgsConstructor
@Entity
@DiscriminatorValue("Movie")
public class Movie extends Item {
    private String director;
    private String actor;

    protected Movie(String name, Long price, Integer stockQuantity,  String Director, String actor){
        super(MOVIE, name, price, stockQuantity);
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

    public static Movie create(ItemBuilder itemBuilder, String director, String actor) throws ItemException {
        Movie.MovieBuilder actBuilder = (Movie.MovieBuilder) itemBuilder;
        return actBuilder
                .director(director)
                .actor(actor)
                .build();
    }

    public static Map<String, String> findField(Movie movie){
        return Map.of("field1", movie.director, "field2", movie.actor);
    }

    public void setField(String field1,String field2) {
        this.director = hasLength(field1)
                ? field1 : this.director ;
        this.actor = hasLength(field2)
                ? field2 : this.actor;
    }
}
