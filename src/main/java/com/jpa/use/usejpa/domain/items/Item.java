package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.domain.Category;
import com.jpa.use.usejpa.domain.OrderItem;
import com.jpa.use.usejpa.exception.ItemException;
import com.jpa.use.usejpa.exception.item.NotEnoughStockException;
import com.jpa.use.usejpa.exception.item.NotValidatePriceException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = SINGLE_TABLE)
@Entity
public class Item {
    @Id @Column(name="item_id")
    @GeneratedValue
    private Long id;
    private String name;
    private Long price;
    private Integer stockQuantity;

    @ManyToMany(mappedBy = "items")
    private final List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    protected Item(String name , Long price, Integer stockQuantity){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public static Item create(String name , Long price, Integer stockQuantity) throws ItemException {
        return Item.createSpecificItemBuilder(null, name, price, stockQuantity)
                .build();
    }

    protected static <T extends Item> ItemBuilder createSpecificItemBuilder(Class<T> clazz, String name , Long price, Integer stockQuantity) throws ItemException {
        checkItemState(price, stockQuantity);

        if(Act.class.equals(clazz)){
            return Act.builder()
                    .name(name)
                    .price(price)
                    .stockQuantity(stockQuantity);
        }
        else if(Book.class.equals(clazz)){
            return Book.builder()
                    .name(name)
                    .price(price)
                    .stockQuantity(stockQuantity);
        }
        else if(Movie.class.equals(clazz)){
            return Movie.builder()
                    .name(name)
                    .price(price)
                    .stockQuantity(stockQuantity);
        }
        return Item.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity);
    }

    /**
     * 재고 증가
     */
    public void addStockQuantity(Integer quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStockQuantity(Integer quantity) throws NotEnoughStockException {
        if(this.stockQuantity < quantity){
            throw new NotEnoughStockException("남아있는 갯수보다 많은 갯수를 제거할 수 없습니다.");
        }
        this.stockQuantity -= quantity;
    }

    protected static void checkItemState(Long price, Integer stockQuantity) throws ItemException {
        if(price<0)             throw new NotValidatePriceException();
        if(stockQuantity < 0)   throw new NotEnoughStockException();
    }
}
