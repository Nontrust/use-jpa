package com.jpa.use.usejpa.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class Address {
    private String addressName;
    private String street;
    private String zipcode;

    public static Address create(String addressName, String street, String zipcode){
        return Address.builder()
                .addressName(addressName)
                .street(street)
                .zipcode(zipcode)
                .build();

    }
}
