package com.realstate.home.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="comment")
@SQLDelete(sql="UPDATE comment SET is_deleted = true WHERE comment_id=?")
@Where(clause = "is_deleted = false")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '댓글 내용'")
    private String content;


    public static Comment of(Member member, Room room, String content) {
        Comment entity = new Comment();
        entity.setMember(member);
        entity.setRoom(room);
        entity.setContent(content);
        return entity;
    }

}
