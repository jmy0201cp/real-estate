package com.realstate.home.controller;

import com.realstate.home.domain.RoomType;
import com.realstate.home.dto.Response;
import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;
import com.realstate.home.service.RoomService;
import lombok.Getter;
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

}
