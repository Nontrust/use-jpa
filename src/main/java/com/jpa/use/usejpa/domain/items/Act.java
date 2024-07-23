package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.exception.ItemException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import static com.jpa.use.usejpa.domain.enumerate.ItemDType.ACT;
import static org.springframework.util.StringUtils.hasLength;

@SuperBuilder
@DiscriminatorValue("Act")
@RequiredArgsConstructor
@Entity
public class Act extends Item {
    private String artist;
    private String etc;

    protected Act(String name, Long price, Integer stockQuantity,  String artist, String etc){
        super(ACT, name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

     public static Act create(String name, Long price, Integer stockQuantity, String artist, String etc) throws ItemException {
        ActBuilder actBuilder = (ActBuilder) Item.createSpecificItemBuilder(Act.class, name, price, stockQuantity);
        return Act.create(actBuilder, artist, etc);
    }

    public static Act create(ItemBuilder itemBuilder, String artist, String etc) throws ItemException {
        ActBuilder actBuilder = (ActBuilder) itemBuilder;
        return actBuilder
                .artist(artist)
                .etc(etc)
                .build();
    }

    public static Map<String, String> findField(Act act){
        return Map.of("field1", act.artist, "field2", act.etc);
    }

    public void setField(String field1,String field2) {
        this.artist = hasLength(field1)
                ? field1 : this.artist;
        this.etc = hasLength(field2)
                ? field2 : this.etc;
    }
}
