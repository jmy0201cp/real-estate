package com.realstate.home.controller;

import com.realstate.home.dto.request.MemberRequest;
import com.realstate.home.dto.response.MemberResponse;
import com.realstate.home.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/signup")
    public MemberResponse join(@RequestBody MemberRequest request) {
        return memberService.signup(request);
    }
}
