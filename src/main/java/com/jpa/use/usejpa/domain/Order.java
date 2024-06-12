package com.jpa.use.usejpa.domain;

import com.jpa.use.usejpa.domain.enumerate.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.jpa.use.usejpa.domain.enumerate.OrderStatus.*;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order {
    @Id @GeneratedValue
    private Long id;

    private ZonedDateTime orderDate;

    @Enumerated(STRING)
    private OrderStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public static Order createOrder(OrderBuilder orderBuilder, Member member, Delivery delivery, OrderItem... orderItems){
        Order order = orderBuilder
                .orderDate(ZonedDateTime.now())
                .status(ORDERED)
                .member(member)
                .delivery(delivery)
                .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    public void cancelOrder(){
        if(CANCELED.equals(delivery.getState())){
            throw new IllegalStateException("배송 완료된 건 입니다.");
        }
        this.setStatus(CANCELED);
        for (OrderItem orderItem : this.orderItems){
            orderItem.cancel();
        }
    }


    protected void setMember (Member member){
        this.member = member;
        member.getOrder().add(this);
    }

    protected void setStatus(OrderStatus status){
        this.status = status;
    }

    protected void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public long getTotalPrice(){
        return this.orderItems.stream()
                .mapToLong(OrderItem::getTotalPrice)
                .sum();
    }
}

