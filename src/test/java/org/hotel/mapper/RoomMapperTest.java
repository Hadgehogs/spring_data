package org.hotel.mapper;

import org.hotel.dto.RoomDtoRq;
import org.hotel.entity.Room;
import org.hotel.entity.RoomType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomMapperTest {
    @Autowired
    RoomMapper roomMapper;

    @Test
    public void CastFromDto() {
        RoomDtoRq input =new RoomDtoRq("666", RoomType.LUX);
        Room expected = roomMapper.CastFromDto(input);
        Room actual = new Room("666",RoomType.LUX);
        Assertions.assertEquals(expected,actual);
    }
}
