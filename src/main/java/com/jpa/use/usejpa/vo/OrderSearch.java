package com.jpa.use.usejpa.vo;


import com.jpa.use.usejpa.domain.enumerate.OrderStatus;

public record OrderSearch(
        String memberName, OrderStatus orderStatus
) {
}
