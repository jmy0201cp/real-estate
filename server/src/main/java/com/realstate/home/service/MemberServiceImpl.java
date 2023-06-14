package com.realstate.home.service;

import com.realstate.home.domain.entity.Member;
import com.realstate.home.dto.request.MemberRequest;
import com.realstate.home.dto.response.MemberResponse;
import com.realstate.home.exception.ErrorCode;
import com.realstate.home.exception.HomeException;
import com.realstate.home.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public MemberResponse signup(MemberRequest request) {
       memberRepository.findByMemberName(request.getMemberName()).ifPresent(it -> {
            throw new HomeException(ErrorCode.DUPLICATED_DATA);
        });
       request.setPassword(encoder.encode(request.getPassword()));
       Member created = memberRepository.save(Member.of(request));
       return MemberResponse.fromEntity(created);
    }
}
