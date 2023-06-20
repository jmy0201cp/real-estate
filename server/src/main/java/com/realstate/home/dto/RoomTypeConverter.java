package com.realstate.home.dto;

import com.realstate.home.domain.RoomType;
import org.springframework.core.convert.converter.Converter;

public class RoomTypeConverter implements Converter<String, RoomType> {

    @Override
    public RoomType convert(String roomType) {
        return RoomType.valueOf(roomType.toUpperCase());
    }
}
