package com.realstate.home.service;

import com.realstate.home.dto.request.CommentRequest;
import com.realstate.home.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    void create(Long roomId, String memberName, CommentRequest request);

    List<CommentResponse> getCommentList(Long roomId);

    CommentResponse update(String memberName, Long commentId, CommentRequest request);

    void delete(String memberName, Long commentId);
}
