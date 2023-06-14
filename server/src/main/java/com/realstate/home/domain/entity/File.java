package com.realstate.home.domain.entity;

import com.realstate.home.dto.request.RoomRequest;
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
@Table(name="file")
@SQLDelete(sql="UPDATE file SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long fileId;

    @Column(columnDefinition = "long NOT NULL COMMENT '방 아이디'")
    private Long roomId;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '파일 이름'")
    private String name;

    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '파일 url'")
    private String url;

    public static File of(Long roomId, String fileName, String fileUrl) {
        File file = new File();
        file.setRoomId(roomId);
        file.setName(fileName);
        file.setUrl(fileUrl);
        return file;
    }

}
