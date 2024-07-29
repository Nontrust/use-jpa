package com.jpa.use.usejpa.vo.request;

public record UpdateMemberRequest(
        String name,
        AddressRequest address
) {
}
