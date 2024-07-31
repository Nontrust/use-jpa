package com.jpa.use.usejpa.service;

import com.jpa.use.usejpa.domain.*;
import com.jpa.use.usejpa.domain.items.Item;
import com.jpa.use.usejpa.exception.item.NotEnoughStockException;
import com.jpa.use.usejpa.repository.ItemRepository;
import com.jpa.use.usejpa.repository.MemberRepository;
import com.jpa.use.usejpa.repository.OrderRepository;
import com.jpa.use.usejpa.vo.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long order(Long memberId, Delivery delivery, Long itemId, Integer count) throws NotEnoughStockException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException(memberId + "에 해당하는 유저가 없습니다."));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException(itemId + "에 해당하는 제품이 없습니다."));

        Address orderAddress = isEmpty(delivery) ? member.getAddress() : delivery.getAddress();
        Delivery orderDelivery = Delivery.createDelivery(orderAddress);

        OrderItem orderItem = OrderItem
                .createOrderItem(item, count, item.getPrice());

        Order order = Order.createOrder(Order.builder(), member, orderDelivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Order> findAll(OrderSearch orderSearch) {
        return orderRepository.findAllBySearchMethods(orderSearch);
    }


    @Transactional
    public void cancel(Long memberId, Long orderId){
        Order order = orderRepository.findByOrderIdAndMemberId(orderId, memberId)
                .orElseThrow(() -> new IllegalStateException("해당하는 제품이 없습니다."));

        order.cancel();
    }

    @Transactional
    public void cancel(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("해당하는 제품이 없습니다."));

        order.cancel();
    }
}
