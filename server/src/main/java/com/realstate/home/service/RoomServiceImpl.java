package com.realstate.home.service;

import com.realstate.home.domain.entity.Room;
import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;
import com.realstate.home.exception.ErrorCode;
import com.realstate.home.exception.HomeException;
import com.realstate.home.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    @Override
    public RoomResponse create(RoomRequest request) {
        Room room = roomRepository.save(Room.of(request));
        return RoomResponse.fromEntity(room);
    }

    @Override
    public RoomResponse getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> {
            throw new HomeException(ErrorCode.DO_NOT_FOUND);
        });

        return RoomResponse.fromEntity(room);
    }

    @Override
    public List<RoomResponse> getRoomList() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(t -> RoomResponse.fromEntity(t)).collect(Collectors.toList());
    }
}
