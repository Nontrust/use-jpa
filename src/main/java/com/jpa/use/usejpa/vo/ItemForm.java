package com.jpa.use.usejpa.vo;

import com.jpa.use.usejpa.domain.enumerate.ItemDType;
import com.jpa.use.usejpa.domain.items.Item;
import com.jpa.use.usejpa.valid.ValidItemDType;
import lombok.Builder;

import java.util.Map;


@Builder
public record ItemForm(
        Long id,
        String name,
        Long price,
        Integer stockQuantity,
        @ValidItemDType
        ItemDType itemDType,
        String field1,
        String field2
) {

    public static ItemForm of(){
        return ItemForm.builder().build();
    }

    public static ItemForm fromEntity(Item item) {
        Map<String, String> field = Item.findField(item);
        return ItemForm.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .field1(field.get("field1"))
                .field2(field.get("field2"))
                .itemDType(item.getItemDType())
                .build();
    }
}
