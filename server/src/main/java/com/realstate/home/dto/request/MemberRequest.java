package com.realstate.home.dto.request;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private String memberName;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
    private String addressDetail;
}
