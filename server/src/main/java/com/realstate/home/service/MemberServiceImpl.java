package com.realstate.home.service;

import com.realstate.home.domain.entity.Member;
import com.realstate.home.dto.request.MemberRequest;
import com.realstate.home.dto.response.MemberResponse;
import com.realstate.home.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse signup(MemberRequest request) {
       Member member = memberRepository.save(Member.of(request));
       return MemberResponse.fromEntity(member);
    }
}
