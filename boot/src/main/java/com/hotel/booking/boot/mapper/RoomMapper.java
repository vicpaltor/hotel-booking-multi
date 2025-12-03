package com.hotel.booking.boot.mapper;

import com.hotel.booking.application.mapper.RoomMapperPort;
import com.hotel.booking.domain.room.Room;
import com.hotel.booking.application.dto.Room.CreateRoomRequestDto;
import com.hotel.booking.application.dto.Room.RoomDto;
import com.hotel.booking.application.dto.Room.UpdateRoomRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper extends RoomMapperPort {
    // Al extender la interfaz del puerto, MapStruct sabe que tiene que
    // implementar los métodos toDto y toEntity automáticamente.
}

