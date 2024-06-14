package com.jpa.use.usejpa.repository;

import com.jpa.use.usejpa.domain.Member;
import com.jpa.use.usejpa.domain.Order;
import com.jpa.use.usejpa.domain.enumerate.OrderStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Optional<Order> findById(Long id){
        return Optional.ofNullable(em.find(Order.class, id));
    }

    public List<Order> findAll(){
        String query = "select o from Order o";
        return em.createQuery(query, Order.class)
                .getResultList();
    }

    public List<Order> findAllByMember(Member member){
        String query = "select o from Order o join o.member m where m = :member";
        return em.createQuery(query, Order.class)
                .setParameter("member", member)
                .getResultList();
    }

    public Optional<Order> findByOrderIdAndMemberId(Long orderId, Long memberId){
        String query = """
                        select o
                            from Order o
                            join o.member m
                            where o.id = :orderId
                                and m.id = :memberId
                        """;
        List<Order> orders = em.createQuery(query, Order.class)
                .setParameter("orderId", orderId)
                .setParameter("memberId", memberId)
                .setMaxResults(1)
                .getResultList();

        return Optional.ofNullable(!orders.isEmpty() ? orders.get(0) : null);
    }

    public List<Order> findAllByOrderStatus(OrderStatus orderStatus){
        String query = "select o from Order o where o.status = :status";
        return em.createQuery(query, Order.class)
                .setParameter("status", orderStatus)
                .getResultList();
    }

}
