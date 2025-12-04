package com.hotel.booking.application.service;

import com.hotel.booking.application.dto.Room.CreateRoomRequestDto;
import com.hotel.booking.application.dto.Room.RoomDto;
import com.hotel.booking.application.mapper.RoomMapperPort;
import com.hotel.booking.application.repository.HotelRepository;
import com.hotel.booking.application.repository.RoomRepository;
import com.hotel.booking.domain.exception.HotelNotFoundException;
import com.hotel.booking.domain.room.Room;

public class RoomService {

    private RoomRepository roomRepository;
    private HotelRepository hotelRepository;
    private RoomMapperPort roomMapper;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository, RoomMapperPort roomMapper) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.roomMapper = roomMapper;
    }

    public RoomDto createRoom(Long hotelId, CreateRoomRequestDto request) {

        boolean hotelExists = hotelRepository.existsById(hotelId);

        if (!hotelExists) {
            throw new HotelNotFoundException("No se puede crear una habitación para un hotel inexistente con id: " + hotelId);
        }

        // 2. Si el hotel existe, procedemos a crear la habitación.
        Room room = roomMapper.toEntity(request);

        // 3. Asignamos el ID del hotel a nuestra nueva habitación.
        room.setHotelId(hotelId);

        // 4. Guardamos la nueva habitación en la base de datos.
        Room savedRoom = roomRepository.save(room);

        // 5. Convertimos la entidad guardada a un DTO y la devolvemos.
        return roomMapper.toDto(savedRoom);
    }
}
