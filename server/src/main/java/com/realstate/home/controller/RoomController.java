package com.realstate.home.controller;

import com.realstate.home.dto.Response;
import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;
import com.realstate.home.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{roomId}")
    public Response<RoomResponse> getRoomById(@PathVariable Long roomId) {
        return Response.success(roomService.getRoomById(roomId));
    }

    @GetMapping
    public Response<List<RoomResponse>> getRoomList() {
        return Response.success(roomService.getRoomList());
    }
}
