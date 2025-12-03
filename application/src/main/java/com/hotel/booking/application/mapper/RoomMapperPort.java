package com.hotel.booking.application.mapper;

import com.hotel.booking.application.dto.Room.CreateRoomRequestDto;
import com.hotel.booking.application.dto.Room.RoomDto;
import com.hotel.booking.application.dto.Room.UpdateRoomRequestDto;
import com.hotel.booking.domain.room.Room;

public interface RoomMapperPort {

    RoomDto toDto(Room room);
    Room toEntity(CreateRoomRequestDto request);
    Room toEntity(UpdateRoomRequestDto request); // ¡Sobrecarga de métodos!

}
