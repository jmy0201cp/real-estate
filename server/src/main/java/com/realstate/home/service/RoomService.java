package com.realstate.home.service;

import com.realstate.home.domain.RoomType;
import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {

    RoomResponse create(RoomRequest request);

    void saveFile(Long roomId, MultipartFile file);

    RoomResponse getRoomById(Long roomId);

    List<RoomResponse> getRoomList(RoomType roomType);

    RoomResponse update(Long roomId, RoomRequest request);

    void delete(Long roomId);

    //위시리스트 추가
    void addWishList(Long memberId, Long roomId);

    boolean isExistInWishList(Long memberId, Long roomId);

    List<RoomResponse> getAllWishListByMemberId(Long memberId);
}
