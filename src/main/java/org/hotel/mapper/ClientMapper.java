package org.hotel.mapper;

import org.hotel.dto.BookingDtoRq;
import org.hotel.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client CastFromDto(BookingDtoRq bookingDtoRq) {
        Client client = new Client();
        client.setName(bookingDtoRq.getClient());
        client.setEmail(bookingDtoRq.getClientEmail());
        return client;
    }

}
