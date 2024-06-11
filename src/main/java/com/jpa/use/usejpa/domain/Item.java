package com.jpa.use.usejpa.domain;

import com.jpa.use.usejpa.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Getter
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = SINGLE_TABLE)
@Entity
public abstract class Item {
    @Id @Column(name="item_id")
    private Long id;
    private String name;
    private Long price;
    private Integer stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**
     * 재고 증가
     */
    public void addStockQuantity(Integer quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStockQuantity(Integer quantity) throws NotEnoughStockException {
        if(this.stockQuantity < quantity){
            throw new NotEnoughStockException("you've not enough quantity");
        }
        this.stockQuantity -= quantity;

    }
}
