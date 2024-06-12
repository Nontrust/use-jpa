package com.jpa.use.usejpa.domain;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Address {
    private String addressName;
    private String street;
    private String zipcode;
}
