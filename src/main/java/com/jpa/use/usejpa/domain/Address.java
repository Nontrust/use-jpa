package com.jpa.use.usejpa.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String name;
    private String street;
    private String zipcode;
}
