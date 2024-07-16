package com.jpa.use.usejpa.controller;

import com.jpa.use.usejpa.domain.Member;
import com.jpa.use.usejpa.service.MemberService;
import com.jpa.use.usejpa.vo.MemberForm;
import com.jpa.use.usejpa.vo.MemberVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm(null, null, null, null));
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMemeber(@Valid MemberForm memberForm, BindingResult result){
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Member member = memberService.toMember(memberForm);
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findAllMember();
        List<MemberVo> memberForms = members.stream()
                .map(MemberVo::fromEntity)
                .toList();

        model.addAttribute("members", memberForms);
        return "members/memberList";
    }

}
