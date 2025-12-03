package com.hotel.booking.infrastructure.repository.jdbc;

import com.hotel.booking.domain.room.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpringRoomRepository extends CrudRepository<Room, Long> {
    // La magia de Spring Data: ¡Generará la consulta SQL solo con el nombre!
    List<Room> findByHotelId(Long hotelId);
}