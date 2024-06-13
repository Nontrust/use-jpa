package com.jpa.use.usejpa.service;

import com.jpa.use.usejpa.domain.*;
import com.jpa.use.usejpa.domain.enumerate.OrderStatus;
import com.jpa.use.usejpa.domain.items.Act;
import com.jpa.use.usejpa.domain.items.Book;
import com.jpa.use.usejpa.domain.items.Item;
import com.jpa.use.usejpa.domain.items.Movie;
import com.jpa.use.usejpa.exception.ItemException;
import com.jpa.use.usejpa.exception.item.NotEnoughStockException;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.ObjectUtils.isEmpty;


@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private OrderService orderService;
    @Test
    public void 상품_주문() throws ItemException {
        //given
        Integer originStock = 10;
        Integer orderItemCnt = 5;

        Integer afterStock = 0;

        Member member = getMember();
        Delivery delivery = Delivery.createDelivery(member.getAddress());
        Book book = Book.create("테스트", 1111L, originStock, "aa", UUID.randomUUID());

        em.persist(member);
        em.persist(delivery);
        em.persist(book);

        //when
        Long orderId = orderService.order(member.getId(), delivery, book.getId(), orderItemCnt);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime plussedNow = ZonedDateTime.now().plusMinutes(1L);

        Order order = em.find(Order.class, orderId);

        try {
            OrderItem orderItem = order.getOrderItems().get(0);
            afterStock = orderItem.getItem().getStockQuantity();
        } catch (NullPointerException e){
            fail("관계 미지정", e);
        }

        //then
        assertEquals(OrderStatus.ORDERED, order.getStatus());
        assertEquals(orderId, order.getId());


        assertEquals(afterStock, originStock - orderItemCnt);
        assertEquals(order.getTotalPrice(), orderItemCnt * book.getPrice());

        // orderedDate
        assertTrue(plussedNow.isAfter(order.getOrderDate()) );
        assertEquals(now.getSecond(), order.getOrderDate().getSecond() );
        assertFalse(isEmpty(order.getId()) );

    }

    private static Member getMember() {
        Address address = Address.create("테스트", "테스트 1-1", "12345");
        Member member = Member.builder()
                .name("회원1")
                .address(address)
                .build();
        return member;
    }

    @Test
    public void 주문_취소() throws ItemException {
        //given
        Integer originStock = 10;
        Integer orderItemCnt = 5;

        Member member = getMember();
        Delivery delivery = Delivery.createDelivery(member.getAddress());
        Book book = Book.create("테스트", 1111L, originStock, "aa", UUID.randomUUID());

        em.persist(member);
        em.persist(delivery);
        em.persist(book);
        Long orderId = orderService.order(member.getId(), delivery, book.getId(), orderItemCnt);

        Integer beforeOrderStockQuantity = book.getStockQuantity();

        //when
        Order order = em.find(Order.class, orderId);
        order.cancel();

        //then
        assertEquals(beforeOrderStockQuantity, originStock - orderItemCnt);
        assertEquals(book.getStockQuantity(), originStock);
    }

    @Test
    public void 상품_주문_예외처리() throws ItemException{
        //given
        Integer originStock = 10;
        Integer orderItemCnt = 15;

        Member member = getMember();
        Delivery delivery = Delivery.createDelivery(member.getAddress());
        Book book = Book.create("테스트", 1111L, originStock, "aa", UUID.randomUUID());

        em.persist(member);
        em.persist(delivery);
        em.persist(book);

        //when
        Assertions.assertThrowsExactly(NotEnoughStockException.class, () -> orderService.order(member.getId(), delivery, book.getId(), orderItemCnt)) ;
        //then
    }

    @Test
    public void 아이템의_서브타입이_정상적으로_등록되는지_테스트합니다() throws Exception, ItemException {
        //given
        Item item = Item.create("일반 아이템", 1L, 1);
        Movie movie = Movie.create("무비 아이템", 1L, 1, "디렉터", "배우");
        Act act = Act.create("무비 아이템", 1L, 1, "디렉터", "배우");
        Book book = Book.create("무비 아이템", 1L, 1, "디렉터", UUID.randomUUID());

        //when
        for(Item i : Arrays.asList(item, movie, act, book)){
            em.persist(i);
        }

        // then
        Assert.isInstanceOf(Item.class, item);
        Assert.isInstanceOf(Item.class, movie);
        Assert.isInstanceOf(Item.class, act);
        Assert.isInstanceOf(Item.class, book);

        Assert.isInstanceOf(Movie.class, movie);
        Assert.isInstanceOf(Act.class, act);
        Assert.isInstanceOf(Book.class, book);

    }

}