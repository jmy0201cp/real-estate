package com.realstate.home.service;

import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {

    RoomResponse create(RoomRequest request);

    RoomResponse getRoomById(Long roomId);

    List<RoomResponse> getRoomList();

}
