package com.jpa.use.usejpa.vo.response;

import com.jpa.use.usejpa.domain.Address;
import lombok.Builder;

@Builder
public record AddressResponse(
        String city,
        String street,
        String zipcode
) {
    public static AddressResponse fromEntity(Address address){
        return AddressResponse.builder()
                .city(address.getCity())
                .street(address.getStreet())
                .zipcode(address.getZipcode())
                .build();
    }

}

