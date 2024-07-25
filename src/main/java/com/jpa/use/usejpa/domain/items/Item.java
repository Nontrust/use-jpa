package com.jpa.use.usejpa.domain.items;

import com.jpa.use.usejpa.domain.Category;
import com.jpa.use.usejpa.domain.OrderItem;
import com.jpa.use.usejpa.domain.enumerate.ItemDType;
import com.jpa.use.usejpa.exception.ItemException;
import com.jpa.use.usejpa.exception.item.NotEnoughStockException;
import com.jpa.use.usejpa.exception.item.NotValidatePriceException;
import com.jpa.use.usejpa.exception.item.NotValidatedItemDTypeException;
import com.jpa.use.usejpa.vo.ItemForm;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jpa.use.usejpa.domain.enumerate.ItemDType.*;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;
import static org.springframework.util.ObjectUtils.isEmpty;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @Transient
    private ItemDType itemDType;

    @ManyToMany(mappedBy = "items")
    private final List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private final List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    protected Item(ItemDType itemDType, String name , Long price, Integer stockQuantity){
        this.itemDType = itemDType;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    @PostLoad
    public void setItemDType() throws NotValidatedItemDTypeException {
        this.itemDType = ItemDType.getDTypes(getClass())
                .orElseThrow(NotValidatedItemDTypeException::new);
    }

    public static ItemBuilder ofBuilder(ItemForm form) throws ItemException {
        Class<? extends Item> dType = form.itemDType().getDType();
        return  createSpecificItemBuilder(dType, form.name(), form.price(), form.stockQuantity());
    }

    public static Item create(String name , Long price, Integer stockQuantity) throws ItemException {
        return Item.createSpecificItemBuilder(null, name, price, stockQuantity)
                .build();
    }

    protected static <T extends Item> ItemBuilder createSpecificItemBuilder(Class<T> clazz, String name , Long price, Integer stockQuantity) throws ItemException {
        checkItemState(price, stockQuantity);

        if(ACT.getDType().equals(clazz)){
            return Act.builder()
                    .name(name)
                    .price(price)
                    .stockQuantity(stockQuantity);
        }
        else if(BOOK.getDType().equals(clazz)){
            return Book.builder()
                    .name(name)
                    .price(price)
                    .stockQuantity(stockQuantity);
        }
        else if(MOVIE.getDType().equals(clazz)){
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


    public void updateItem(ItemForm itemForm) throws ItemException {
        updateItemCommonField(itemForm.name(), itemForm.price(), itemForm.stockQuantity());
        String field1 = itemForm.field1();
        String field2 = itemForm.field2();

        switch (this.getItemDType()){
            case BOOK   -> {
                Book book = (Book) this;
                book.setField(field1, field2);
            }
            case MOVIE  ->{
                Movie movie = (Movie) this;
                movie.setField(field1, field2);
            }
            case ACT    -> {
                Act act = (Act) this;
                act.setField(field1, field2);
            }
            case ITEM -> {}
        };
    }

    public void updateItemCommonField(String name, Long price, Integer stockQuantity) throws ItemException {
        this.name = !isEmpty(name)
                ? name : this.name ;
        this.price = !isEmpty(price)
                ? price : this.price ;
        this.stockQuantity = !isEmpty(stockQuantity)
                ? stockQuantity : this.stockQuantity ;
        checkItemState(this.price, this.stockQuantity);
    }

    public static Map<String, String> findField(Item item) {
        return switch(item.itemDType){
            case BOOK   -> Book.findField((Book) item);
            case MOVIE  -> Movie.findField((Movie) item);
            case ACT    -> Act.findField((Act)item);
            case ITEM   -> new HashMap<>();
        };
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
