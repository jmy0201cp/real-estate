package com.realstate.home.dto.response;

import com.realstate.home.domain.entity.Comment;
import com.realstate.home.domain.entity.Member;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long commentId;
    private Long memberId;
    private Long roomId;
    private String memberName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getMember().getMemberId(),
                comment.getRoom().getRoomId(),
                comment.getMember().getMemberName(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

}
