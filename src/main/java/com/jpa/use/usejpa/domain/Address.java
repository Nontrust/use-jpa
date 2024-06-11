package com.jpa.use.usejpa.domain;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@RequiredArgsConstructor
public class Address {
    private String addressName;
    private String street;
    private String zipcode;
}
