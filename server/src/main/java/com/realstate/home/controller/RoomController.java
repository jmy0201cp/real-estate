package com.realstate.home.controller;

import com.realstate.home.domain.RoomType;
import com.realstate.home.dto.Response;
import com.realstate.home.dto.request.CommentRequest;
import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.CommentResponse;
import com.realstate.home.dto.response.MemberResponse;
import com.realstate.home.dto.response.RoomResponse;
import com.realstate.home.service.CommentService;
import com.realstate.home.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final CommentService commentService;

    @PostMapping
    public Response<RoomResponse> create(@RequestBody RoomRequest request) {
        return Response.success(roomService.create(request));
    }

    @PostMapping("/{roomId}")
    public Response<Void> saveFile(@PathVariable Long roomId, MultipartFile file) {
        roomService.saveFile(roomId, file);
        return Response.success();
    }

    @GetMapping("/{roomId}")
    public Response<RoomResponse> getRoomById(@PathVariable Long roomId) {
        return Response.success(roomService.getRoomById(roomId));
    }

    @GetMapping
    public Response<List<RoomResponse>> getRoomList(@RequestParam RoomType roomType) {
        return Response.success(roomService.getRoomList(roomType));
    }

    @PutMapping("/{roomId}")
    public Response<RoomResponse> update(@PathVariable Long roomId, @RequestBody RoomRequest request) {
        return Response.success(roomService.update(roomId, request));
    }

    @DeleteMapping("/{roomId}")
    public Response<Void> delete(@PathVariable Long roomId) {
        roomService.delete(roomId);
        return Response.success();
    }

    //위시리스트에 넣기
    @PostMapping("/{roomId}/wishlist")
    public Response<Void> addWishlist(@PathVariable Long roomId, Authentication authentication) {
        MemberResponse member = (MemberResponse) authentication.getPrincipal();
        roomService.addWishList(member.getMemberId(), roomId);
        return Response.success();
    }

    @GetMapping("/{roomId}/wishlist")
    public Response<Boolean> isExistRoomInWishList(@PathVariable Long roomId, Authentication authentication) {
        MemberResponse member = (MemberResponse) authentication.getPrincipal();
        return Response.success(roomService.isExistInWishList(member.getMemberId(), roomId));
    }

    //댓글
    @PostMapping("/{roomId}/comment")
    public Response<Void> createComment(@PathVariable Long roomId, Authentication authentication, @RequestBody CommentRequest request) {
        MemberResponse member = (MemberResponse) authentication.getPrincipal();
        commentService.create(roomId, member.getMemberId(), request);
        return Response.success();
    }

    @GetMapping("/{roomId}/comment")
    public Response<List<CommentResponse>> getCommentList(@PathVariable Long roomId) {
       return Response.success(commentService.getCommentList(roomId));
    }

    @PutMapping("/comment/{commentId}")
    public Response<CommentResponse> updateComment(@PathVariable Long commentId, Authentication authentication, @RequestBody CommentRequest request) {
        MemberResponse member = (MemberResponse) authentication.getPrincipal();
        return Response.success(commentService.update(member.getMemberId(), commentId, request));
    }

    @DeleteMapping("/comment/{commentId}")
    public Response<Void> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        MemberResponse member = (MemberResponse) authentication.getPrincipal();
        commentService.delete(member.getMemberId(), commentId);
        return Response.success();
    }
}
