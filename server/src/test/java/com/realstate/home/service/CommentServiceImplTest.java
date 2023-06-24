package com.realstate.home.service;

import com.realstate.home.domain.entity.Comment;
import com.realstate.home.domain.entity.Member;
import com.realstate.home.domain.entity.Room;
import com.realstate.home.dto.request.CommentRequest;
import com.realstate.home.dto.response.CommentResponse;
import com.realstate.home.repository.CommentRepository;
import com.realstate.home.repository.MemberRepository;
import com.realstate.home.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @InjectMocks
    CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private RoomRepository roomRepository;

    /* --- 댓글 기능 ---- */

    // 방아이디로 댓글 모두 조회하기
    @Test
    void Should_ResponseCommentAllList_When_RequestGetCommentsByRoomId() {
        Long roomId = 2L;
        Long commentId1 = 1L;
        Long commentId2 = 2L;

        Member member = Member.builder()
                .memberId(1L)
                .memberName("miyoung")
                .build();

        Room room = Room.builder()
                .roomId(2L)
                .build();

        Comment entity1 = Comment.builder()
                .commentId(1L)
                .member(member)
                .room(room)
                .build();

        Comment entity2 = Comment.builder()
                .commentId(2L)
                .member(member)
                .room(room)
                .build();

        //given
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(commentRepository.findAllByRoomOrderByCreatedAtDesc(room)).thenReturn((Arrays.asList(entity1, entity2)));

        //when

        List<CommentResponse> actualList = commentService.getCommentList(roomId);

        //then
        assertThat(actualList.get(0).getCommentId()).isSameAs(commentId1);
        assertThat(actualList.get(1).getCommentId()).isSameAs(commentId2);
    }

    // 방아이디로 댓글 작성하기
    @Test
    void Should_InsertComment_When_RequestCreateCommentByRoomId() {
        Long roomId = 2L;
        String memberName = "miyoung";
        Long commentId = 1L;

        Member member = Member.builder()
                .memberId(1L)
                .memberName("miyoung")
                .build();

        Room room = Room.builder()
                .roomId(2L)
                .build();

        CommentRequest request = CommentRequest.builder()
                .content("대출 가능한가요?")
                .build();

        Comment entity = Comment.builder()
                .commentId(1L)
                .member(member)
                .room(room)
                .build();

        //given
        when(memberRepository.findByMemberName(memberName)).thenReturn(Optional.of(member));
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(commentRepository.save(ArgumentMatchers.any(Comment.class))).thenReturn(entity);

        //when
        commentService.create(roomId, memberName, request);

        //then
        assertThat(entity.getMember().getMemberName()).isSameAs(memberName);
        assertThat(entity.getRoom().getRoomId()).isSameAs(roomId);
        assertThat(entity.getCommentId()).isSameAs(commentId);
    }

    // 댓글수정
    @Test
    void Should_ResponseCommentEntity_When_RequestUpdateCommentByCommentId() {
        Long commentId = 1L;
        Long memberId = 1L;
        String memberName = "miyoung";
        String content = "보증금 조절 원합니다.";

        Member member = Member.builder()
                .memberId(1L)
                .memberName("miyoung")
                .build();

        Room room = Room.builder()
                .roomId(2L)
                .build();

        Comment entity = Comment.builder()
                .commentId(1L)
                .member(member)
                .room(room)
                .content("전세 대출 가능합니다.")
                .build();

        CommentRequest request = CommentRequest.builder()
                .content(content)
                .build();

        //given
        when(memberRepository.findByMemberName(memberName)).thenReturn(Optional.of(member));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(entity));
        when(commentRepository.save(ArgumentMatchers.any(Comment.class))).thenReturn(entity);

        //when
        CommentResponse actual = commentService.update(memberName, commentId, request);

        //then
        assertThat(actual.getCommentId()).isSameAs(commentId);
        assertThat(actual.getMemberId()).isSameAs(memberId);
        assertThat(actual.getContent()).isSameAs(content);
    }

    // 댓글삭제
    @Test
    void Should_Delete_When_RequestDeleteCommentByCommentId() {
        Long commentId = 1L;
        Long memberId = 1L;
        String memberName = "miyoung";

        Member member = Member.builder()
                .memberId(1L)
                .memberName("miyoung")
                .build();

        Room room = Room.builder()
                .roomId(2L)
                .build();

        Comment entity = Comment.builder()
                .commentId(1L)
                .member(member)
                .room(room)
                .content("전세 대출 가능합니다.")
                .build();

        //given
        when(memberRepository.findByMemberName(memberName)).thenReturn(Optional.of(member));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(entity));

        //when
        commentService.delete(memberName, commentId);

        //then
        assertThat(entity.getMember().getMemberId()).isSameAs(memberId);
        assertThat(entity.getMember().getMemberName()).isSameAs(memberName);
        assertThat(entity.getCommentId()).isSameAs(commentId);
    }

}