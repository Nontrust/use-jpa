package com.jpa.use.usejpa.service;

import com.jpa.use.usejpa.domain.items.Act;
import com.jpa.use.usejpa.domain.items.Book;
import com.jpa.use.usejpa.domain.items.Item;
import com.jpa.use.usejpa.domain.items.Movie;
import com.jpa.use.usejpa.exception.ItemException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.UUID;


@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Test
    public void 아이템의_서브타입이_정상적으로_등록되는지_테스트합니다() throws Exception, ItemException {

        Item item = Item.create("일반 아이템", 1L, 1);
        Movie movie = Movie.create("무비 아이템", 1L, 1, "디렉터", "배우");
        Act act = Act.create("무비 아이템", 1L, 1, "디렉터", "배우");
        Book book = Book.create("무비 아이템", 1L, 1, "디렉터", UUID.randomUUID());


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