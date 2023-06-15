package com.realstate.home.service;

import com.realstate.home.domain.entity.File;
import com.realstate.home.domain.entity.Room;
import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;
import com.realstate.home.exception.ErrorCode;
import com.realstate.home.exception.HomeException;
import com.realstate.home.repository.FileRepository;
import com.realstate.home.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final FileRepository fileRepository;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Transactional
    @Override
    public RoomResponse create(RoomRequest request) {
        Room room = roomRepository.save(Room.of(request));
        return RoomResponse.fromEntity(room);
    }

    @Transactional
    @Override
    public void saveFile(Long roomId, MultipartFile file) {
        //해당 아이디의 방이 존재하는지
        Room room = getRoomEntityOrExceptionById(roomId);

        try {
            String roomPath = uploadPath.concat("/" + roomId);
            Path dirPath = Paths.get(roomPath);

            if(!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }

            InputStream inputSteam = file.getInputStream();

            Files.copy(inputSteam, dirPath.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);

            fileRepository.save(File.of(room.getRoomId(), file.getOriginalFilename(), roomPath));
            inputSteam.close();

        } catch (Exception e) {
            throw new HomeException(ErrorCode.SERVER_ERROR);
        }

    }

    @Override
    public RoomResponse getRoomById(Long roomId) {
        Room room = getRoomEntityOrExceptionById(roomId);

        return RoomResponse.fromEntity(room);
    }

    @Override
    public List<RoomResponse> getRoomList() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(t -> RoomResponse.fromEntity(t)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public RoomResponse update(Long roomId, RoomRequest request) {
        getRoomEntityOrExceptionById(roomId);

        Room entity = Room.of(request);
        entity.setRoomId(roomId);
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setIsDeleted(false);

        return RoomResponse.fromEntity(roomRepository.save(entity));
    }

    @Override
    public void delete(Long roomId) {
        Room room = getRoomEntityOrExceptionById(roomId);
        roomRepository.delete(room);
    }

    private Room getRoomEntityOrExceptionById(Long roomId) {
       return roomRepository.findById(roomId).orElseThrow(() -> {
            throw new HomeException(ErrorCode.DO_NOT_FOUND);
        });
    }
}
