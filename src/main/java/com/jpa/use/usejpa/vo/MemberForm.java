package com.jpa.use.usejpa.vo;

import jakarta.validation.constraints.NotEmpty;

public record MemberForm
    (
            @NotEmpty(message = "이름은 필수입니다.") String name,
            String city,
            String street,
            String zipcode
    )
{}
