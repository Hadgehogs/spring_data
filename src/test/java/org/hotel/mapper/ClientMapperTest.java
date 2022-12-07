package org.hotel.mapper;

import lombok.SneakyThrows;
import org.hotel.dto.BookingDtoRq;
import org.hotel.entity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ClientMapperTest {

    @Autowired
    ClientMapper clientMapper;

    @Test
    @SneakyThrows
    public void CastFromDto() {
        LocalDate beginDate = LocalDate.of(2022,10,1);
        LocalDate endDate = LocalDate.of(2022,10,30);

        BookingDtoRq input = new BookingDtoRq("666",beginDate,endDate,"АтеистыПодОгнем","Hadgehogs@yandex.ru");
        Client expected = clientMapper.CastFromDto(input);
        Client actual = new Client("АтеистыПодОгнем","Hadgehogs@yandex.ru");
        Assertions.assertEquals(expected,actual);
    }
}
