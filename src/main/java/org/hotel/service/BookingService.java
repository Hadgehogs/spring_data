package org.hotel.service;

import lombok.AllArgsConstructor;
import org.hotel.core.Result;
import org.hotel.dao.BookingDao;
import org.hotel.dao.ClientDao;
import org.hotel.dao.RoomDao;
import org.hotel.dto.BookingDtoRq;
import org.hotel.dto.BookingDtoRs;
import org.hotel.entity.Booking;
import org.hotel.entity.Client;
import org.hotel.mapper.BookingMapper;
import org.hotel.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingService {
    BookingMapper bookingMapper;
    BookingDao bookingDao;
    ClientDao clientDao;
    public Result createBooking(BookingDtoRq bookingDtoRq) {
        Result result = new Result();
        try {
            Booking booking = bookingMapper.CastFromDto(bookingDtoRq);
            if (bookingDtoRq.getBeginDate().isAfter(bookingDtoRq.getEndDate())){
                result.setResult(false);
                result.setErrorDescription("Дата начала бронирования больше даты завершения");
                return result;
            }
            List<Booking> buzyBookings = bookingDao.checkBookingForBuzy(bookingDtoRq.getRoom(), bookingDtoRq.getBeginDate(), bookingDtoRq.getEndDate());
            if (buzyBookings.size()>0){
                result.setResult(false);
                result.setErrorDescription("Номер уже забронирован");
                return result;
            }

            bookingDao.save(booking);
        } catch (RuntimeException runtimeException) {
            result.setResult(false);
            result.setErrorDescription(runtimeException.getMessage());
        }
        return result;
    }
    public List<BookingDtoRs> getClientBookings(String clientName) {
        Optional<Client> clientOptional = clientDao.findById(clientName);
        if (clientOptional.isEmpty()) {
            throw new RuntimeException(String.format("Не найден клиент с именем %s",clientName));
        }

        Client client=clientOptional.get();

        List<Booking> clientBooking = bookingDao.findByClient(client);
        return clientBooking.stream().map(booking -> bookingMapper.CastFromEntity(booking)).collect(Collectors.toList());
    }

    public List<BookingDtoRs> getBookingByNumber(Integer bookingNumber) {
        List<Booking> clientBooking = bookingDao.findByNumber(bookingNumber);
        List<BookingDtoRs> result = clientBooking.stream().map(booking -> bookingMapper.CastFromEntity(booking)).collect(Collectors.toList());
        return result;
    }


    public Result deleteBookingByNumber(Integer bookingNumber) {
        Result result = new Result();
        try {
            bookingDao.deleteById(bookingNumber);
        } catch (RuntimeException runtimeException) {
            result.setResult(false);
            result.setErrorDescription(runtimeException.getMessage());
        }
        return result;
    }
}
