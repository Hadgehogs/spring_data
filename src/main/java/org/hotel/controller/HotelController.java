package org.hotel.controller;

import lombok.AllArgsConstructor;
import org.hotel.core.Result;
import org.hotel.dto.RoomDtoRq;
import org.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HotelController {
    HotelService hotelService;

    @PostMapping(path = "/createRoom")
    public ResponseEntity<String> createRoom(@RequestBody RoomDtoRq roomDtoRq) {
        return Result.castResponseEntityFromResult(hotelService.createRoom(roomDtoRq));

    }


}
