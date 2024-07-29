package com.jpa.use.usejpa.vo.request;

public record AddressRequest(
        String city,
        String street,
        String zipcode
) {}
