package com.realstate.home.service;

import com.realstate.home.domain.ContractType;
import com.realstate.home.domain.RoomType;
import com.realstate.home.domain.entity.Member;
import com.realstate.home.domain.entity.Room;
import com.realstate.home.domain.entity.Wishlist;
import com.realstate.home.dto.request.RoomRequest;
import com.realstate.home.dto.response.RoomResponse;
import com.realstate.home.repository.MemberRepository;
import com.realstate.home.repository.RoomRepository;
import com.realstate.home.repository.WishRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

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

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private WishRepository wishRepository;

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
        ContractType contractType = ContractType.JEONSE;

        //given
        Room beforeRoom = Room.builder()
                .roomId(2L)
                .url("dkfhse/gdsel.g")
                .contractType(ContractType.MONTHLY)
                .price(BigDecimal.valueOf(700000))
                .managementFee(BigDecimal.valueOf(60000))
                .build();

        Room afterRoom = Room.builder()
                .roomId(2L)
                .url("dkfhse/gdsel.g")
                .contractType(ContractType.JEONSE)
                .price(BigDecimal.valueOf(125000000))
                .managementFee(BigDecimal.valueOf(70000))
                .build();

        RoomRequest request = RoomRequest.builder()
                .url("dkf.g")
                .contractType(ContractType.JEONSE)
                .price(BigDecimal.valueOf(125000000))
                .managementFee(BigDecimal.valueOf(70000))
                .build();

        when(roomRepository.findById(findId)).thenReturn(Optional.of(beforeRoom));
        when(roomRepository.save(ArgumentMatchers.any(Room.class))).thenReturn(afterRoom);

        //when
        RoomResponse actual = roomService.update(findId, request);

        //then
        assertThat(actual.getRoomId()).isSameAs(findId);
        assertThat(actual.getContractType()).isSameAs(contractType);
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

    //위시리스트 추가/삭제
    @Test
    void Should_AddWishList_When_RequestCreateInWishList() {
        Long memberId = 1L;
        Long roomId = 2L;
        Long wishId = 2L;

        Member member = Member.builder()
                .memberId(1L)
                .memberName("miyoung")
                .build();

        Room room = Room.builder()
                .roomId(2L)
                .build();

        Wishlist entity = Wishlist.builder()
                .wishId(2L)
                .member(member)
                .room(room)
                .build();

        //given
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(wishRepository.findByMemberAndRoom(member, room)).thenReturn(entity);

        //when
        roomService.addWishList(memberId, roomId);

        //then
        assertThat(entity.getWishId()).isSameAs(wishId);
        assertThat(entity.getMember().getMemberId()).isSameAs(memberId);
        assertThat(entity.getRoom().getRoomId()).isSameAs(roomId);
    }

    // 위시리스트에 이미 존재하는지
    @Test
    void IsExistAlready_InWishList_When_RequestCheckWishById() {
        Long roomId = 2L;
        Long memberId = 1L;

        Member member = Member.builder()
                .memberId(1L)
                .memberName("miyoung")
                .build();

        Room room = Room.builder()
                .roomId(2L)
                .build();

        Wishlist entity = Wishlist.builder()
                .wishId(2L)
                .member(member)
                .room(room)
                .build();

        //given
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(wishRepository.findByMemberAndRoom(member, room)).thenReturn(entity);

        //when
        Boolean actual = roomService.isExistInWishList(memberId, roomId);

        //then
        assertThat(actual).isSameAs(true);
    }

    // 위시리스트 모두 조회
    @Test
    void Should_ResponseWishAllList_When_RequestGetWishListByMemberName() {
        Long roomId1 = 1L;
        Long roomId2 = 2L;
        Long memberId = 1L;

        Member member = Member.builder()
                .memberId(1L)
                .memberName("miyoung")
                .build();

        Room room1 = Room.builder()
                .roomId(1L)
                .build();

        Room room2 = Room.builder()
                .roomId(2L)
                .build();

        Wishlist entity1 = Wishlist.builder()
                .wishId(2L)
                .member(member)
                .room(room1)
                .build();

        Wishlist entity2 = Wishlist.builder()
                .wishId(3L)
                .member(member)
                .room(room2)
                .build();

        //given
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(wishRepository.findAllByMember(member)).thenReturn(Optional.of(Arrays.asList(entity1, entity2)));
        when(roomRepository.findById(roomId1)).thenReturn(Optional.of(room1));
        when(roomRepository.findById(roomId2)).thenReturn(Optional.of(room2));

        //when
        List<RoomResponse> actualList = roomService.getAllWishListByMemberId(memberId);

        //then
        assertThat(actualList.size()).isSameAs(Arrays.asList(entity1, entity2).size());
        assertThat(actualList.get(0).getRoomId()).isSameAs(roomId1);
        assertThat(actualList.get(1).getRoomId()).isSameAs(roomId2);
    }
}