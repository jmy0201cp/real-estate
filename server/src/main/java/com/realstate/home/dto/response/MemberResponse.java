package com.realstate.home.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.realstate.home.domain.entity.Member;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberResponse implements UserDetails {
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
    private Boolean isDeleted;

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
                member.getUpdatedAt(),
                member.getIsDeleted()
        );
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getMemberId().toString()));
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.memberName;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return this.isDeleted = false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return this.isDeleted = false;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return this.isDeleted = false;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.isDeleted = false;
    }
}
