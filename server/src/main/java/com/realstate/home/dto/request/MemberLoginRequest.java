package com.realstate.home.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginRequest {
    private String memberName;
    private String password;
}
