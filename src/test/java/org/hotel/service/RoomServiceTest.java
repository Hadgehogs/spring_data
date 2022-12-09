package org.hotel.service;

import lombok.SneakyThrows;
import org.hotel.core.Result;
import org.hotel.dao.BookingDao;
import org.hotel.dao.ClientDao;
import org.hotel.dao.RoomDao;
import org.hotel.dto.BookingDtoRq;
import org.hotel.dto.BookingDtoRs;
import org.hotel.dto.RoomDtoRq;
import org.hotel.entity.Booking;
import org.hotel.entity.Client;
import org.hotel.entity.Room;
import org.hotel.entity.RoomType;
import org.hotel.mapper.BookingMapper;
import org.hotel.mapper.RoomMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RoomServiceTest {
    @Autowired
    RoomService roomService;
    @MockBean
    RoomMapper roomMapper;
    @Autowired
    RoomDao roomDao;

    @Test
    public void createRoom() {
        RoomDtoRq roomDtoRq=new RoomDtoRq("777", RoomType.LUX);
        Room actual=new Room(roomDtoRq.getName(),roomDtoRq.getRoomType());
        Mockito.when(roomMapper.CastFromDto(roomDtoRq)).thenReturn(actual);
        Result result = roomService.createRoom(roomDtoRq);
        Assertions.assertTrue(result.isResult());

        Optional<Room> expectedOptional=roomDao.findById(actual.getName());
        Assertions.assertTrue(expectedOptional.isPresent());
        Room expected=expectedOptional.get();
        Assertions.assertEquals(expected,actual);
    }
}
