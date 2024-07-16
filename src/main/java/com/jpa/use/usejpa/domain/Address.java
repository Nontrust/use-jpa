package com.jpa.use.usejpa.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;

    public static Address create(String city, String street, String zipcode){
        return Address.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();

    }
}
