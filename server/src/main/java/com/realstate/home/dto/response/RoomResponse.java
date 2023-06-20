package com.realstate.home.dto.response;

import com.realstate.home.domain.ContractType;
import com.realstate.home.domain.RoomType;
import com.realstate.home.domain.entity.Member;
import com.realstate.home.domain.entity.Room;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    private Long roomId;
    private String url;
    private RoomType roomType;
    private ContractType contractType;
    private BigDecimal price;
    private String address;
    private String addressDetail;
    private BigDecimal managementFee;
    private Boolean elevatorYn;
    private Integer roomCount;
    private Integer floor;
    private String details;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;

    public static RoomResponse fromEntity(Room room) {
        return new RoomResponse(
                room.getRoomId(),
                room.getUrl(),
                room.getRoomType(),
                room.getContractType(),
                room.getPrice(),
                room.getAddress(),
                room.getAddressDetail(),
                room.getManagementFee(),
                room.getElevatorYn(),
                room.getRoomCount(),
                room.getFloor(),
                room.getDetails(),
                room.getCreatedAt(),
                room.getUpdatedAt(),
                room.getIsDeleted()
        );
    }
}
