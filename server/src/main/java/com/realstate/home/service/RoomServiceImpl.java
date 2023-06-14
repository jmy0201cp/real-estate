package com.realstate.home.service;

import com.realstate.home.domain.entity.Room;
import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;
import com.realstate.home.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    @Override
    public RoomResponse create(RoomRequest request) {
        Room room = roomRepository.save(Room.of(request));
        return RoomResponse.fromEntity(room);
    }
}
