package org.hotel.dao;

import org.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<Room, String> {


}

