package org.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hotel.entity.RoomType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoRq {
    private String name;
    private RoomType roomType;
}
