package com.realstate.home.domain.entity;


import com.realstate.home.domain.ContractType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="room")
@SQLDelete(sql="UPDATE room SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long roomId;

    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '방 이미지'")
    private String url;

    @Column(columnDefinition = "varchar(20) DEFAULT NULL COMMENT '계약 유형(매매,전세,월세)'")
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @Column(columnDefinition = "decimal(15,2) DEFAULT NULL COMMENT '방 금액'")
    private BigDecimal price;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '주소'")
    private String address;

    @Column(columnDefinition = "varchar(50) DEFAULT NULL COMMENT '상세주소'")
    private String addressDetail;

    @Column(columnDefinition = "decimal(8,2) DEFAULT NULL COMMENT '관리비'")
    private BigDecimal managementFee;

    @Column(columnDefinition = "boolean DEFAULT false COMMENT '엘레베이터 유무'")
    private Boolean elevatorYn;

    @Column(columnDefinition = "Integer DEFAULT 1 COMMENT '방 갯수'")
    private Integer roomCount;

    @Column(columnDefinition = "Integer DEFAULT 1 COMMENT '방 층수'")
    private Integer floor;

    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '상세정보'")
    private String details;

}
