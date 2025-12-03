package com.hotel.booking.application.repository;

import com.hotel.booking.domain.room.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    Room save(Room room);
    List<Room> findByHotelId(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    Optional<Room> findById(Long id);
}


//SE TERMINA DE CONFIGURAR REPOSITORY !!!!SE PRUEBA¡¡¡¡