package org.hotel.controller;

import lombok.AllArgsConstructor;
import org.hotel.core.Result;
import org.hotel.dto.BookingDtoRq;
import org.hotel.dto.BookingDtoRs;
import org.hotel.dto.RoomDtoRq;
import org.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HotelController {
    HotelService hotelService;
    @PostMapping(path = "/createRoom")
    public ResponseEntity<String> createRoom(@RequestBody RoomDtoRq roomDtoRq) {
        return Result.castResponseEntityFromResult(hotelService.createRoom(roomDtoRq));

    }

    @PostMapping(path = "/createBooking")
    public ResponseEntity<String> createBooking(@RequestBody BookingDtoRq bookingDtoRq) {
        return Result.castResponseEntityFromResult(hotelService.createBooking(bookingDtoRq));

    }

    @GetMapping(path = "/getClientBookings/{clientName}")
    public ResponseEntity getClientBookings(@PathVariable String clientName) {
        try {
            List<BookingDtoRs> clientBookings = hotelService.getClientBookings(clientName);
            return new ResponseEntity<>(clientBookings, HttpStatus.OK);
        }
        catch (RuntimeException runtimeException){
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/getBookingByNumber/{bookingNumber}")
    public ResponseEntity<Object> getBookingByNumber(@PathVariable Integer bookingNumber) {
        try {
            List<BookingDtoRs> bookings = hotelService.getBookingByNumber(bookingNumber);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
        catch (RuntimeException runtimeException){
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping (path = "/deleteBookingByNumber/{bookingNumber}")
    public ResponseEntity<String> deleteBookingByNumber(@PathVariable Integer bookingNumber) {
        return Result.castResponseEntityFromResult(hotelService.deleteBookingByNumber(bookingNumber));

    }
}
