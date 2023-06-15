package com.realstate.home.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginResponse {
    private String memberName;
    private String token;
}
