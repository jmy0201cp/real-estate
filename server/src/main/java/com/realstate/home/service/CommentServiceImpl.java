package com.realstate.home.service;

import com.realstate.home.domain.entity.Comment;
import com.realstate.home.domain.entity.Member;
import com.realstate.home.domain.entity.Room;
import com.realstate.home.dto.request.CommentRequest;
import com.realstate.home.dto.response.CommentResponse;
import com.realstate.home.exception.ErrorCode;
import com.realstate.home.exception.HomeException;
import com.realstate.home.repository.CommentRepository;
import com.realstate.home.repository.MemberRepository;
import com.realstate.home.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

        private final CommentRepository commentRepository;
        private final MemberRepository memberRepository;
        private final RoomRepository roomRepository;

    @Override
    public void create(Long roomId, String memberName, CommentRequest request) {
        Member member = getMemberEntityOrException(memberName);
        Room room = getRoomEntityOrException(roomId);

        commentRepository.save(Comment.of(member, room, request.getContent()));
    }

    @Override
    public List<CommentResponse> getCommentList(Long roomId) {
        // 방 아이디 있는지 확인
        Room room = getRoomEntityOrException(roomId);

        List<Comment> commentList = commentRepository.findAllByRoomOrderByCreatedAtDesc(room);

        return commentList.stream().map(t -> CommentResponse.fromEntity(t)).collect(Collectors.toList());
    }

    @Override
    public CommentResponse update(String memberName, Long commentId, CommentRequest request) {
        Member member = getMemberEntityOrException(memberName);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new HomeException(ErrorCode.DO_NOT_FOUND);
        });

        //지금 로그인 유저와 댓글 작성자가 동일한지
        if(member.getMemberId() != comment.getMember().getMemberId()) {
            throw new HomeException(ErrorCode.DO_NOT_MATCH);
        }

        comment.setUpdatedAt(LocalDateTime.now());
        comment.setContent(request.getContent());

        return CommentResponse.fromEntity(commentRepository.save(comment));
    }

    @Override
    public void delete(String memberName, Long commentId) {
        //해당 유저가 존재하는지
        Member member = getMemberEntityOrException(memberName);

        //해당 댓글이 존재하는지
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new HomeException(ErrorCode.DO_NOT_FOUND);
        });

        //지금 로그인 유저와 댓글 작성자가 동일한지
        if(member.getMemberId() != comment.getMember().getMemberId()) {
            throw new HomeException(ErrorCode.DO_NOT_MATCH);
        }

        commentRepository.delete(comment);
    }

    private Room getRoomEntityOrException(Long roomId) {
       return roomRepository.findById(roomId).orElseThrow(() -> {
            throw new HomeException(ErrorCode.DO_NOT_FOUND);
        });
    }

    private Member getMemberEntityOrException(String memberName) {
        return memberRepository.findByMemberName(memberName).orElseThrow(() -> {
            throw new HomeException(ErrorCode.USER_NOT_FOUND);
        });
    }
}
