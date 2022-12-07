package org.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtoRq {
    private String room;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String client;
    private String clientEmail;
}
