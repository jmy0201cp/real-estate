package com.realstate.home.service;

import com.realstate.home.dto.request.CommentRequest;
import com.realstate.home.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    void create(Long roomId, Long memberId, CommentRequest request);

    List<CommentResponse> getCommentList(Long roomId);

    CommentResponse update(Long memberId, Long commentId, CommentRequest request);

    void delete(Long memberId, Long commentId);

}
