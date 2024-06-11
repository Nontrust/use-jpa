package com.jpa.use.usejpa.repository;

import com.jpa.use.usejpa.domain.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null){
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(em.find(Item.class ,id));
    }

    public Optional<Item> findByName(String name) {
        String query = "select i from Item i where i.name = :name";
        List<Item> items = em.createQuery(query, Item.class)
                .setParameter("name", name)
                .getResultList();

        return Optional.ofNullable(!items.isEmpty() ? items.get(0) : null );
    }

    public List<Item> findAll() {
        String query = "select i from Item i";
        return em.createQuery(query, Item.class)
                .getResultList();

    }
}
