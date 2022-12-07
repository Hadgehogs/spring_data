package org.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hotel.entity.Room;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtoRs {
    private Room room;
    private LocalDate beginDate;
    private LocalDate endDate;
}
