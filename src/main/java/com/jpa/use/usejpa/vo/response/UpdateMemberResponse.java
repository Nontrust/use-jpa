package com.jpa.use.usejpa.vo.response;

import com.jpa.use.usejpa.domain.Member;
import lombok.Builder;

@Builder
public record UpdateMemberResponse(
        Long id,
        String name,
        AddressResponse address
) {
    public static UpdateMemberResponse fromEntity(Member member) {
        AddressResponse addressResponse = AddressResponse.fromEntity(member.getAddress());
        return UpdateMemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .address(addressResponse)
                .build();

    }
}
