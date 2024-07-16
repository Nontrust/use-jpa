package com.jpa.use.usejpa.domain;

import com.jpa.use.usejpa.vo.MemberForm;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

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
}
