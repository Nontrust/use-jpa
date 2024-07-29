package com.jpa.use.usejpa.service;

import com.jpa.use.usejpa.domain.items.Act;
import com.jpa.use.usejpa.domain.items.Book;
import com.jpa.use.usejpa.domain.items.Item;
import com.jpa.use.usejpa.domain.items.Movie;
import com.jpa.use.usejpa.exception.ItemException;
import com.jpa.use.usejpa.repository.ItemRepository;
import com.jpa.use.usejpa.vo.ItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item findOne(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(()->new IllegalStateException(id + " 에 해당하는 Item이 없습니다."));
    }

    @Transactional(readOnly = true)
    public ItemForm mapToVo(Item item) {
        return ItemForm.fromEntity(item);
    }

    @Transactional
    public Item create(ItemForm form) throws ItemException {
        Item.ItemBuilder itemBuilder = Item.ofBuilder(form);
        return switch (form.itemDType()) {
            case ITEM   -> itemBuilder.build();
            case ACT    -> Act.create(itemBuilder, form.field1(), form.field2());
            case BOOK   -> Book.create(itemBuilder, form.field1(), form.field2());
            case MOVIE  -> Movie.create(itemBuilder, form.field1(), form.field2());
        };
    }

    @Transactional
    public void updateItem(Long itemId, ItemForm form) throws ItemException {
        Item item = findOne(itemId);
        item.updateItem(form);
    }
}
