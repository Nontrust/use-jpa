package com.jpa.use.usejpa.repository;

import com.jpa.use.usejpa.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Optional<Member> findById(Long id){
        return Optional.ofNullable(em.find(Member.class, id));
    }
    public Optional<Member> findByName(String name){
        String query= "select m from Member m where name = :name";
        List<Member> members = em.createQuery(query, Member.class)
                .setParameter("name", name)
                .getResultList();
        return Optional.ofNullable(!members.isEmpty() ? members.get(0) : null );
    }

    public boolean existById(Long id){
        return this.findById(id).isPresent();
    }
    public boolean existByName(String name){
        return this.findByName(name).isPresent();
    }

    public List<Member> findAll(){
        String query =
                """
                    select m from Member m
                """;
        return em.createQuery(query, Member.class)
                .getResultList();
    }

    public List<Member> findAllByName(String name) {
        String query =
                """
                    select m from Member m where name = :name
                """;
        return em.createQuery(query, Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
