package org.hotel.controller;

import lombok.AllArgsConstructor;
import org.hotel.core.Result;
import org.hotel.dto.BookingDtoRq;
import org.hotel.dto.BookingDtoRs;
import org.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingController {
    BookingService bookingService;

    @PostMapping(path = "/createBooking")
    public ResponseEntity<String> createBooking(@RequestBody BookingDtoRq bookingDtoRq) {
        return Result.castResponseEntityFromResult(bookingService.createBooking(bookingDtoRq));

    }

    @GetMapping(path = "/getClientBookings")
    public ResponseEntity getClientBookings(@RequestParam String clientName) {
        try {
            List<BookingDtoRs> clientBookings = bookingService.getClientBookings(clientName);
            return new ResponseEntity<>(clientBookings, HttpStatus.OK);
        }
        catch (RuntimeException runtimeException){
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/getBookingByNumber")
    public ResponseEntity<Object> getBookingByNumber(@RequestParam Integer bookingNumber) {
        try {
            List<BookingDtoRs> bookings = bookingService.getBookingByNumber(bookingNumber);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }
        catch (RuntimeException runtimeException){
            return new ResponseEntity<>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping (path = "/deleteBookingByNumber")
    public ResponseEntity<String> deleteBookingByNumber(@RequestParam Integer bookingNumber) {
        return Result.castResponseEntityFromResult(bookingService.deleteBookingByNumber(bookingNumber));
    }
}
