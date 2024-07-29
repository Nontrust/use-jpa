package com.jpa.use.usejpa.service;

import com.jpa.use.usejpa.domain.Member;
import com.jpa.use.usejpa.repository.MemberRepository;
import com.jpa.use.usejpa.vo.MemberForm;
import com.jpa.use.usejpa.vo.request.CreateMemberRequest;
import com.jpa.use.usejpa.vo.request.UpdateMemberRequest;
import com.jpa.use.usejpa.vo.response.UpdateMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional
    public Member toMember(MemberForm form){
        return Member.of(form);
    }

    @Transactional
    public Member toMember(CreateMemberRequest request){
        return Member.of(request);
    }

    @Transactional
    public Long join(Member member){
        validationDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    @Transactional(readOnly = true)
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id+"에 해당하는 멤버가 없습니다."));
    }

    @Transactional
    public UpdateMemberResponse update(Long memberId, UpdateMemberRequest request){
        Member member = findMemberById(memberId);
        member.update();

        return UpdateMemberResponse.fromEntity(member);
    }

    /***
     * utils
     */
    private void validationDuplicateMember(Member member){
        if(memberRepository.existByName(member.getName())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
