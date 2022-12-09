package org.hotel.service;

import lombok.AllArgsConstructor;
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
import org.hotel.mapper.BookingMapper;
import org.hotel.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {
    RoomMapper roomMapper;
    RoomDao roomDao;

    public Result createRoom(RoomDtoRq roomDtoRq) {
        Result result = new Result();

        try {
            Room room = roomMapper.CastFromDto(roomDtoRq);
            if (roomDao.existsById(roomDtoRq.getName())){
                result.setResult(false);
                result.setErrorDescription(String.format("Комната в гостинице с номером %s уже создана",roomDtoRq.getName()));
                return result;
            }
            roomDao.save(room);
        } catch (RuntimeException runtimeException) {
            result.setResult(false);
            result.setErrorDescription(runtimeException.getMessage());
        }
        return result;
    }


}
