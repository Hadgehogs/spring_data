package org.hotel.mapper;

import org.hotel.core.PropertyValuesFiller;
import org.hotel.dto.RoomDtoRq;
import org.hotel.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public Room CastFromDto(RoomDtoRq roomDtoRq) {
        Room room = new Room();
        PropertyValuesFiller.FillPropertyValues(room, roomDtoRq,null, "name");
        return room;
    }

}
