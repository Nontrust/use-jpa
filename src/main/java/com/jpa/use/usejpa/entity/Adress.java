package com.jpa.use.usejpa.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Adress {
    private String name;
    private String street;
    private String zipcode;
}
