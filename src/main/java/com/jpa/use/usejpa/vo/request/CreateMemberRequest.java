package com.jpa.use.usejpa.vo.request;


import jakarta.validation.constraints.NotNull;

public record CreateMemberRequest(
        @NotNull String name,
        AddressRequest addressRequest
) {


}
