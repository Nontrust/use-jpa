package com.jpa.use.usejpa.vo;

import com.jpa.use.usejpa.domain.Address;
import com.jpa.use.usejpa.domain.Member;

public record MemberVo
    (
            Long id,
            String name,
            String city,
            String street,
            String zipcode
    )
{
    public static MemberVo fromEntity(final Member member){
        Address address = member.getAddress();
        return new MemberVo(member.getId(), member.getName(), address.getCity(), address.getStreet(), address.getZipcode());
    }

}
