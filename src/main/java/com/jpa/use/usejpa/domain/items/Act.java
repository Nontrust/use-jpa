package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.domain.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("act")
public class Act extends Item {
    private String artist;
    private String etc;
}
