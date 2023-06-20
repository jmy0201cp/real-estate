package com.realstate.home.service;

import com.realstate.home.domain.entity.Member;
import com.realstate.home.dto.request.MemberLoginRequest;
import com.realstate.home.dto.request.MemberRequest;
import com.realstate.home.dto.response.MemberLoginResponse;
import com.realstate.home.dto.response.MemberResponse;
import com.realstate.home.exception.ErrorCode;
import com.realstate.home.exception.HomeException;
import com.realstate.home.repository.MemberRepository;
import com.realstate.home.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;


    public MemberResponse loadUserByMemberName(String memberName) {
        return memberRepository.findByMemberName(memberName).map(MemberResponse::fromEntity).orElseThrow(() ->
           new HomeException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public MemberResponse signup(MemberRequest request) {
       memberRepository.findByMemberName(request.getMemberName()).ifPresent(it -> {
            throw new HomeException(ErrorCode.DUPLICATED_DATA);
        });
       request.setPassword(encoder.encode(request.getPassword()));
       Member created = memberRepository.save(Member.of(request));
       return MemberResponse.fromEntity(created);
    }

    @Override
    public MemberLoginResponse login(MemberLoginRequest request) {
        Member member = memberRepository.findByMemberName(request.getMemberName()).orElseThrow(() -> {
           throw new HomeException(ErrorCode.USER_NOT_FOUND);
        });

        if(!encoder.matches(request.getPassword(), member.getPassword())) {
            throw new HomeException(ErrorCode.PASSWORD_IS_NOT_VALID);
        }
        String token = JwtTokenUtils.createJwtToken(request.getMemberName(), secretKey, expiredTimeMs);

        return MemberLoginResponse.builder()
                .memberName(member.getMemberName())
                .token(token)
                .build();
    }
}
