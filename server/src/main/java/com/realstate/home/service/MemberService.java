package com.realstate.home.service;

import com.realstate.home.dto.request.MemberRequest;
import com.realstate.home.dto.response.MemberResponse;

public interface MemberService {
    MemberResponse signup(MemberRequest request);
}
