package com.jpa.use.usejpa.domain;

import com.jpa.use.usejpa.vo.MemberForm;
import com.jpa.use.usejpa.vo.request.AddressRequest;
import com.jpa.use.usejpa.vo.request.CreateMemberRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;
import static org.springframework.util.ObjectUtils.isEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> order;


    public static Member of(MemberForm m){
        Address address = Address.create(m.city(), m.street(), m.zipcode());
        return Member.builder()
                .name(m.name())
                .address(address)
                .build();
    }

    public static Member of(CreateMemberRequest m){
        AddressRequest a = m.addressRequest();
        Address address = isEmpty(a) ?
                Address.create(a.city(), a.street(), a.zipcode()) : null;
        return Member.builder()
                .name(m.name())
                .address(address)
                .build();
    }

    public void update(){

    }
}
