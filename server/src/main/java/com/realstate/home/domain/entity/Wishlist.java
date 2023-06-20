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
@Table(name="wishlist")
@SQLDelete(sql="UPDATE wishlist SET is_deleted = true WHERE wish_id=?")
@Where(clause = "is_deleted = false")
public class Wishlist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long wishId;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;


    public static Wishlist of(Member member, Room room) {
        Wishlist entity = new Wishlist();
        entity.setMember(member);
        entity.setRoom(room);
        return entity;
    }

}
