package org.hotel.dto;

import lombok.Data;
import org.hotel.entity.RoomType;

@Data
public class RoomDtoRq {
    private String name;
    private RoomType roomType;
}
