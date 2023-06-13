package com.realstate.home.domain;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '비밀번호'")
    private String password;

    @Column(columnDefinition = "varchar(20) DEFAULT NULL COMMENT '핸드폰 번호'")
    private String phoneNumber;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '이메일'")
    private String email;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '주소'")
    private String address;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '상세주소'")
    private String addressDetail;

    @Column(columnDefinition = "varchar(5) DEFAULT NULL COMMENT '우편번호'")
    private String zipCode;
}
