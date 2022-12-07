package org.hotel.dao;

import org.hotel.entity.Booking;
import org.hotel.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingDao extends JpaRepository<Booking, Integer> {
    @Query(value = "select * from booking b where b.room_name=:roomName and b.begin_date<=:endDate and b.end_date>=:beginDate", nativeQuery = true)
    List<Booking> checkBookingForBuzy(@Param("roomName") String roomName, @Param("beginDate") LocalDate beginDate, @Param("endDate") LocalDate endDate);
    List<Booking> findByClient(Client client);
    List<Booking> findByNumber(Integer number);
}

