package com.realstate.home.service;

import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {

    RoomResponse create(RoomRequest request);

    void saveFile(Long roomId, MultipartFile file);

    RoomResponse getRoomById(Long roomId);

    List<RoomResponse> getRoomList();

}
