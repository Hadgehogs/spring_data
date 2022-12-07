package org.hotel.mapper;

import lombok.SneakyThrows;
import org.hotel.dao.RoomDao;
import org.hotel.dto.BookingDtoRq;
import org.hotel.entity.Booking;
import org.hotel.entity.Client;
import org.hotel.entity.Room;
import org.hotel.entity.RoomType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class BookingMapperTest {
    @Autowired
    BookingMapper bookingMapper;
    @MockBean
    RoomDao roomDao;

    @Test
    @SneakyThrows
    public void CastFromDto() {
        Room targetRoom=new Room("666", RoomType.LUX);
        Client targetClient=new Client("АтеистыПодОгнем","Hadgehogs@yandex.ru");
        LocalDate beginDate = LocalDate.of(2022,10,1);
        LocalDate endDate = LocalDate.of(2022,10,30);

        Mockito.when(roomDao.findById("666")).thenReturn(Optional.of(targetRoom));

        // clientDao мы не будем переопределять, один фиг он запишется по требованию

        BookingDtoRq input = new BookingDtoRq("666",beginDate,endDate,targetClient.getName(),targetClient.getEmail());
        Booking expected = bookingMapper.CastFromDto(input);
        Booking actual = new Booking(null,targetRoom,beginDate,endDate,targetClient);
        Assertions.assertEquals(expected,actual);
    }
}

