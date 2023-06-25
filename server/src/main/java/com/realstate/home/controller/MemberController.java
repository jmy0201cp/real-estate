package com.realstate.home.controller;

import com.realstate.home.dto.Response;
import com.realstate.home.dto.request.MemberLoginRequest;
import com.realstate.home.dto.request.MemberRequest;
import com.realstate.home.dto.response.MemberLoginResponse;
import com.realstate.home.dto.response.MemberResponse;
import com.realstate.home.dto.response.RoomResponse;
import com.realstate.home.service.MemberService;
import com.realstate.home.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final RoomService roomService;

    @PostMapping("/signup")
    public Response<MemberResponse> join(@RequestBody MemberRequest request) {
        return Response.success(memberService.signup(request));
    }

    @PostMapping("/login")
    public Response<MemberLoginResponse> login(@RequestBody MemberLoginRequest request) {
        return Response.success(memberService.login(request));
    }

    @GetMapping("/wish")
    public Response<List<RoomResponse>> getAllWishListByMemberId(Authentication authentication) {
        MemberResponse member = (MemberResponse) authentication.getPrincipal();
        return Response.success(roomService.getAllWishListByMemberId(member.getMemberId()));
    }

}
