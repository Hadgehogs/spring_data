package org.hotel.controller;

import lombok.AllArgsConstructor;
import org.hotel.core.Result;
import org.hotel.dto.RoomDtoRq;
import org.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    RoomService roomService;
    @PostMapping(path = "/createRoom")
    public ResponseEntity<String> createRoom(@RequestBody RoomDtoRq roomDtoRq) {
        return Result.castResponseEntityFromResult(roomService.createRoom(roomDtoRq));

    }
}
