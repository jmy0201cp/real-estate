package com.realstate.home.dto.request;

import com.realstate.home.domain.ContractType;
import com.realstate.home.domain.RoomType;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {
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
}
