package com.realstate.home.service;

import com.realstate.home.domain.ContractType;
import com.realstate.home.domain.RoomType;
import com.realstate.home.domain.entity.Room;
import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;
import com.realstate.home.repository.FileRepository;
import com.realstate.home.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @InjectMocks
    RoomServiceImpl roomService;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(roomService, "uploadPath", "/Users/miyoung/projects/files");
    }

    @Test
    void Should_SaveRoom_When_RequestCreateRoom() {
        //given
        Room entity = Room.builder()
                .url("dkfhse/gdsel.g")
                .contractType(ContractType.JEONSE)
                .price(BigDecimal.valueOf(125000000))
                .address("서울시 강서구")
                .addressDetail("강서로 11")
                .managementFee(BigDecimal.valueOf(70000))
                .elevatorYn(false)
                .roomCount(2)
                .floor(3)
                .details("전세 대출 가능합니다.")
                .build();

        RoomRequest request = RoomRequest.builder()
                .url("dkfhse/gdsel.g")
                .contractType(ContractType.JEONSE)
                .price(BigDecimal.valueOf(125000000))
                .address("서울시 강서구")
                .addressDetail("강서로 11")
                .managementFee(BigDecimal.valueOf(70000))
                .elevatorYn(false)
                .roomCount(2)
                .floor(3)
                .details("전세 대출 가능합니다.")
                .build();

        when(roomRepository.save(ArgumentMatchers.any(Room.class))).thenReturn(entity);

        //when

        //then
        Assertions.assertDoesNotThrow(() -> roomService.create(request));

    }

    @Test
    void Should_ResponseVoidRoom_When_RequestGetRoomById() {
        Long findId = 1L;

        //given
        Room entity = Room.builder()
                .roomId(1L)
                .build();

        when(roomRepository.findById(findId)).thenReturn(Optional.of(entity));

        //when
        RoomResponse actual = roomService.getRoomById(findId);

        //then
//        Assertions.assertDoesNotThrow(() -> roomService.getRoomById(findId));
        assertThat(actual.getRoomId()).isSameAs(findId);

    }

    @Test
    void Should_ResponseRoomEntities_When_RequestGetRoomAllList() {
        Long firstId = 2L;
        Long secondId = 3L;
        RoomType roomType = RoomType.STUDIO;

        //given
        Room entity1 = Room.builder()
                .roomId(2L)
                .url("dkfhse/gdsel.g")
                .roomType(roomType)
                .contractType(ContractType.JEONSE)
                .price(BigDecimal.valueOf(125000000))
                .address("서울시 강서구")
                .addressDetail("강서로 11")
                .managementFee(BigDecimal.valueOf(70000))
                .elevatorYn(false)
                .roomCount(1)
                .floor(3)
                .details("전세 대출 가능합니다.")
                .build();

        Room entity2 = Room.builder()
                .roomId(3L)
                .url("advertising.jpg")
                .roomType(RoomType.VILLA)
                .contractType(ContractType.MONTHLY)
                .price(BigDecimal.valueOf(600000))
                .address("인천시 남동구")
                .addressDetail("논현동 444")
                .managementFee(BigDecimal.valueOf(150000))
                .elevatorYn(true)
                .roomCount(2)
                .floor(11)
                .details("월세 조정 가능합니다. ")
                .build();

//        when(roomRepository.findAll()).thenReturn(Arrays.asList(entity1, entity2));
        when(roomRepository.findAllByRoomType(RoomType.VILLA)).thenReturn(Arrays.asList(entity2));

        //when
        List<RoomResponse> actualList = roomService.getRoomList(RoomType.VILLA);

        //then
        assertThat(actualList.stream().map(t -> t.getRoomId())).contains(secondId);
//        Assertions.assertDoesNotThrow(()->roomService.getRoomList());
    }

    @Test
    void Should_ResponseRoomEntity_When_RequestUpdateRoomById() {
        Long findId = 2L;

        //given
        Room entity = Room.builder()
                .roomId(2L)
                .url("dkfhse/gdsel.g")
                .contractType(ContractType.JEONSE)
                .price(BigDecimal.valueOf(125000000))
                .managementFee(BigDecimal.valueOf(70000))
                .build();

        RoomRequest request = RoomRequest.builder()
                .url("dkfhse/gdsel.g")
                .contractType(ContractType.MONTHLY)
                .price(BigDecimal.valueOf(125000000))
                .managementFee(BigDecimal.valueOf(70000))
                .build();

        when(roomRepository.findById(findId)).thenReturn(Optional.of(entity));
        when(roomRepository.save(ArgumentMatchers.any(Room.class))).thenReturn(entity);

        //when
        RoomResponse actual = roomService.update(findId, request);

        //then
        assertThat(actual.getRoomId()).isSameAs(findId);
        assertThat(actual.getContractType()).isSameAs(request.getContractType());
    }

    @Test
    void Should_DeleteRoom_When_RequestDeleteRoomById() {
        Long findId = 2L;

        //given
        Room entity = Room.builder()
                .roomId(2L)
                .build();

        when(roomRepository.findById(findId)).thenReturn(Optional.of(entity));

        //when then
        Assertions.assertDoesNotThrow(() -> roomService.delete(findId));
    }

}