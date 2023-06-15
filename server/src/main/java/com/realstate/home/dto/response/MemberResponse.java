package com.realstate.home.dto.response;

import com.realstate.home.domain.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private Long memberId;
    private String memberName;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
    private String addressDetail;
    private String zipCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberResponse fromEntity(Member member) {
        return new MemberResponse(
                member.getMemberId(),
                member.getMemberName(),
                member.getPassword(),
                member.getPhoneNumber(),
                member.getEmail(),
                member.getAddress(),
                member.getAddressDetail(),
                member.getZipCode(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }
}
