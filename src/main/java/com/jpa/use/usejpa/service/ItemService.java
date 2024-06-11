package com.jpa.use.usejpa.service;

import com.jpa.use.usejpa.domain.Item;
import com.jpa.use.usejpa.repository.ItemRepository;
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
}
