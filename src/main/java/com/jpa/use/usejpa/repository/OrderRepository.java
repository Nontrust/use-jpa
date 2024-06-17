package com.jpa.use.usejpa.repository;

import com.jpa.use.usejpa.domain.Member;
import com.jpa.use.usejpa.domain.Order;
import com.jpa.use.usejpa.domain.enumerate.OrderStatus;
import com.jpa.use.usejpa.vo.OrderSearch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

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

    public List<Order> findAllBySearchMethods(OrderSearch orderSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        List<Predicate> condition = new ArrayList<>();

        Root<Order> from = cq.from(Order.class);
        cq.select(from);


        if(!isEmpty(orderSearch.orderStatus())) {
            condition.add(
                    cb.equal(from.get("status"),
                    orderSearch.orderStatus())
            );
        }
        if(!isEmpty(orderSearch.memberName())){
            condition.add(
                    cb.like(from.get("member"),
                    "%"+orderSearch.memberName()+"%")
            );
        }

        cq.where(condition.toArray(new Predicate[0]));

        return em.createQuery(cq)
                .getResultList();
    }

    public List<Order> findAllByOrderStatus(OrderStatus orderStatus){
        String query = "select o from Order o where o.status = :status";
        return em.createQuery(query, Order.class)
                .setParameter("status", orderStatus)
                .getResultList();
    }

}
