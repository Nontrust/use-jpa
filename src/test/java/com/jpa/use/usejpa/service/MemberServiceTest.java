package com.jpa.use.usejpa.service;

import com.jpa.use.usejpa.domain.Member;
import com.jpa.use.usejpa.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntityManager em;


    @Test
    public void 회원가입을_테스트합니다() throws Exception {
        // given
        Member member = Member.builder().name("이충호").build();
        // when
        Long id = memberService.join(member);
        // then
        Assertions.assertEquals(member,  memberRepository.findById(id).orElseThrow());

    }

    @Test
    public void 회원가입_중복체크를_테스트합니다() throws Exception {
        // given
        Member existingMember = Member.builder().name("충호").build();
        memberService.join(existingMember);

        // when
        Member duplicatedMember = Member.builder().name("충호").build();

        // then
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(duplicatedMember));

    }

}