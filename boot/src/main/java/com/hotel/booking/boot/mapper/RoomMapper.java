package com.hotel.booking.boot.mapper;

import com.hotel.booking.domain.room.Room;
import com.hotel.booking.application.dto.Room.CreateRoomRequestDto;
import com.hotel.booking.application.dto.Room.RoomDto;
import com.hotel.booking.application.dto.Room.UpdateRoomRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    // Convierte una Entidad a su DTO de respuesta principal
    RoomDto toDto(Room room);

    // Convierte un DTO de creación a una Entidad
    Room toEntity(CreateRoomRequestDto createDto);

    // Convierte un DTO de actualización a una Entidad
    Room toEntity(UpdateRoomRequestDto updateDto);
}

