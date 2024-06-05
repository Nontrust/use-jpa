package com.jpa.use.usejpa.entity.items;

import com.jpa.use.usejpa.entity.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("movie")
public class Movie extends Item {
    private String Director;
    private String actor;
}
