package com.jpa.use.usejpa.domain;

import com.jpa.use.usejpa.domain.enumerate.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;

import static com.jpa.use.usejpa.domain.enumerate.DeliveryStatus.SUPPOSED;
import static jakarta.persistence.EnumType.STRING;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Delivery {
    @Id @GeneratedValue
    private Long id;
    private Address address;

    @Enumerated(STRING)
    private DeliveryStatus state;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    public static Delivery createDelivery(Address address) {
        return createDelivery(address, SUPPOSED);
    }

    public static Delivery createDelivery(Address address, DeliveryStatus state) {
        Assert.notNull(address, "배송 받을 주소를 입력해 주세요");

        return Delivery.builder()
                .address(address)
                .state(state)
                .build();
    }

    protected void setOrder(Order order){
        this.order = order;
    }

}
