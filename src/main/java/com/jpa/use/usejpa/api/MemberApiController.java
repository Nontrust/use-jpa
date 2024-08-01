package com.jpa.use.usejpa.api;

import com.jpa.use.usejpa.domain.Member;
import com.jpa.use.usejpa.service.MemberService;
import com.jpa.use.usejpa.vo.request.CreateMemberRequest;
import com.jpa.use.usejpa.vo.request.UpdateMemberRequest;
import com.jpa.use.usejpa.vo.response.CreateMemberResponse;
import com.jpa.use.usejpa.vo.response.UpdateMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/members")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping
    public CreateMemberResponse saveMember(@RequestBody @Validated CreateMemberRequest request){

        Member member = memberService.toMember(request);
        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @PutMapping("/{memberId}")
    public UpdateMemberResponse updateMember(
            @PathVariable("memberId") Long memberId,
            @RequestBody @Validated UpdateMemberRequest request
    ){

        return memberService.update(memberId, request);
    }
}
