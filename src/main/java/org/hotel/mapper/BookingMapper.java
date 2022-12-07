package org.hotel.mapper;

import lombok.AllArgsConstructor;
import org.hotel.core.PropertyValuesFiller;
import org.hotel.dao.ClientDao;
import org.hotel.dao.RoomDao;
import org.hotel.dto.BookingDtoRq;
import org.hotel.dto.BookingDtoRs;
import org.hotel.entity.Booking;
import org.hotel.entity.Client;
import org.hotel.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingMapper {

    RoomDao roomDao;
    ClientDao clientDao;
    ClientMapper clientMapper;

    public Booking CastFromDto(BookingDtoRq bookingDtoRq) {
        Booking booking = new Booking();
        PropertyValuesFiller.FillPropertyValues(booking, bookingDtoRq, null, "room,client, clientEmail");
        Optional<Room> roomById = roomDao.findById(bookingDtoRq.getRoom());
        if (roomById.isEmpty()) {
            throw new RuntimeException(String.format("Не найден номер для бронирования с номером %s", bookingDtoRq.getRoom()));
        }
        booking.setRoom(roomById.get());
        Client client;
        Optional<Client> clientById = clientDao.findById(bookingDtoRq.getClient());
        if (clientById.isEmpty()) {
            client = clientMapper.CastFromDto(bookingDtoRq);
            clientDao.save(client);
        } else {
            client = clientById.get();
        }
        booking.setClient(client);
        return booking;
    }

    public BookingDtoRs CastFromEntity(Booking booking) {
        BookingDtoRs bookingDtoRq = new BookingDtoRs();
        PropertyValuesFiller.FillPropertyValues(bookingDtoRq, booking);

        return bookingDtoRq;
    }

}
