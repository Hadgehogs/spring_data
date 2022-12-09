package org.hotel.service;

import lombok.SneakyThrows;
import org.hotel.core.Result;
import org.hotel.dao.BookingDao;
import org.hotel.dao.ClientDao;
import org.hotel.dao.RoomDao;
import org.hotel.dto.BookingDtoRq;
import org.hotel.dto.BookingDtoRs;
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
public class BookingServiceTest {
    @Autowired
    BookingService bookingService;
    @MockBean
    RoomMapper roomMapper;
    @MockBean
    BookingMapper bookingMapper;
    @Autowired
    RoomDao roomDao;
    @Autowired
    BookingDao bookingDao;
    @Autowired
    ClientDao clientDao;
    @Autowired
    EntityManager entityManager;
    Room testRoom;
    Client testClient;
    Booking testBooking;

    @PostConstruct
    public void init(){
        testRoom=new Room("666", RoomType.LUX);
        testClient=new Client("АтеистыПодОгнем","Hadgehogs@yandex.ru");
        roomDao.save(testRoom);
        clientDao.save(testClient);
        LocalDate beginDate = LocalDate.of(2022,10,1);
        LocalDate endDate = LocalDate.of(2022,10,30);

        testBooking=new Booking(null,testRoom,beginDate,endDate,testClient);
        bookingDao.save(testBooking);
    }

    @AfterEach
    public void done(){
        bookingDao.deleteAll();
        clientDao.deleteAll();
        roomDao.deleteAll();
    }

    @Test
    @SneakyThrows
    public void createBooking() {

        LocalDate beginDate = LocalDate.of(2021,10,1);
        LocalDate endDate = LocalDate.of(2021,10,30);

        BookingDtoRq bookingDtoRq=new BookingDtoRq(testRoom.getName(),beginDate,endDate,testClient.getName(),testClient.getEmail());
        Booking actual = new Booking(null,testRoom,beginDate,endDate,testClient);
        Mockito.when(bookingMapper.CastFromDto(bookingDtoRq)).thenReturn(actual);
        Result result = bookingService.createBooking(bookingDtoRq);
        Assertions.assertTrue(result.isResult());

        // У нас номер бронирования - автонумератор Integer, менеджер последовательности, настало твое время, Валера
        Query query = entityManager.createNativeQuery("select last_value from booking_number_seq");
        BigInteger lastNumber = (BigInteger) query.getSingleResult();
        Integer lastBookingNumber = lastNumber.intValue();
        actual.setNumber(lastBookingNumber);

        Optional<Booking> expectedOptional=bookingDao.findById(lastBookingNumber);
        Assertions.assertTrue(expectedOptional.isPresent());
        Booking expected=expectedOptional.get();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void getClientBookings() {
        BookingDtoRs actual=new BookingDtoRs(testBooking.getRoom(),testBooking.getBeginDate(),testBooking.getEndDate());
        Mockito.when(bookingMapper.CastFromEntity(testBooking)).thenReturn(actual);
        List<BookingDtoRs> clientBookings = bookingService.getClientBookings(testClient.getName());
        Assertions.assertEquals(1, clientBookings.size());

        BookingDtoRs expected = clientBookings.get(0);
        Assertions.assertEquals(expected,actual);
    }
//getBookingByNumber пропустим, он похож на getClientBookings
    @Test
    public void deleteBookingByNumber() {
        LocalDate beginDate = LocalDate.of(2021,10,1);
        LocalDate endDate = LocalDate.of(2021,10,30);
        Booking bookingForDelete=new Booking(null,testRoom,beginDate,endDate,testClient);
        bookingDao.save(bookingForDelete);

        Result result = bookingService.deleteBookingByNumber(bookingForDelete.getNumber());
        Assertions.assertTrue(result.isResult());
        Assertions.assertFalse(bookingDao.existsById(bookingForDelete.getNumber()));
    }
}
