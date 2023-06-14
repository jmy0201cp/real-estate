package com.realstate.home.service;

import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;

public interface RoomService {

    RoomResponse create(RoomRequest request);
}
