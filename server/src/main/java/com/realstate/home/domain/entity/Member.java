package com.realstate.home.domain.entity;


import com.realstate.home.dto.request.MemberRequest;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert // 컬럼의 지정된 default 값을 적용시키며 INSERT 할 때 사용
@DynamicUpdate
@Table(name="member")
@SQLDelete(sql="UPDATE member SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long memberId;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '회원 이름'")
    private String memberName;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '비밀번호'")
    private String password;

    @Column(columnDefinition = "varchar(20) DEFAULT NULL COMMENT '핸드폰 번호'")
    private String phoneNumber;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '이메일'")
    private String email;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '원하는 지역(시, 구)'")
    private String address;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '원하는 지역(동/로)'")
    private String addressDetail;

    public static Member of(MemberRequest request) {
        Member member = new Member();
        member.setMemberName(request.getMemberName());
        member.setPassword(request.getPassword());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setEmail(request.getEmail());
        member.setAddress(request.getAddress());
        member.setAddressDetail(request.getAddressDetail());
        return member;
    }
}
