package com.realstate.home.service;

import com.realstate.home.domain.entity.Member;
import com.realstate.home.dto.request.MemberLoginRequest;
import com.realstate.home.dto.request.MemberRequest;
import com.realstate.home.dto.response.MemberLoginResponse;
import com.realstate.home.dto.response.MemberResponse;
import com.realstate.home.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @InjectMocks
    MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    //단위테스트에서는 서비스안에서 구현되어있는 @Value 을 읽을 수 없음, 그래서 강제로 값을 넣어줌.ㄴㄷ
    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(memberService, "secretKey", "real_estate.find-my-perfect-room.secret-key");
        ReflectionTestUtils.setField(memberService, "expiredTimeMs", 25223L);
    }

    //회원가입 후 정보가 반환되는지
    @Test
    void Should_memberEntity_When_RequestSignupMember() {
        //given
        //   >> 반환하는 값이 이 데이터와 똑같아야한다! 라고 내가 모조품을 만든거임
        Member entity = Member.builder()
                .memberName("miyoung")
                .password("abc123")
                .email("ab@gmail.com")
                .address("서울시 강서구")
                .addressDetail("내발산동 11")
                .phoneNumber("010-8888-4777")
                .build();

        MemberRequest request = MemberRequest.builder()
                .memberName("miyoung")
                .password("abc123")
                .email("ab@gmail.com")
                .address("서울시 강서구")
                .addressDetail("내발산동 11")
                .phoneNumber("010-8888-4777")
                .build();

        when(memberRepository.findByMemberName(request.getMemberName())).thenReturn(Optional.empty());
        when(encoder.encode(request.getPassword())).thenReturn("encrypt_password");
        when(memberRepository.save(ArgumentMatchers.any(Member.class))).thenReturn(entity);

        //when
        MemberResponse actual = memberService.signup(request);

        //then
        assertThat(actual.getMemberName()).isSameAs(entity.getMemberName());

//        Assertions.assertDoesNotThrow(() -> memberService.signup(request));
    }

    @Test
    void Should_memberLoginEntity_When_RequestLoginMember() {
        String name = "miyoung";
        String password = "abc123";

        //given
        Member entity = Member.builder()
                .memberName(name)
                .password(password)
                .build();

        MemberLoginRequest request = MemberLoginRequest.builder()
                .memberName("miyoung")
                .password("abc123")
                .build();

        when(memberRepository.findByMemberName(name)).thenReturn(Optional.ofNullable(entity));
        when(encoder.matches(request.getPassword(), password)).thenReturn(true);

        //when
        MemberLoginResponse actual = memberService.login(request);

        //then
        assertThat(actual.getMemberName()).isSameAs(name);

//        Assertions.assertDoesNotThrow(() -> memberService.signup(request));
    }

}