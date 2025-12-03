package com.hotel.booking.boot.controller;


import com.hotel.booking.application.dto.Room.CreateRoomRequestDto;
import com.hotel.booking.application.dto.Room.RoomDto;
import com.hotel.booking.application.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Mantenemos una ruta base genérica
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/hotels/{hotelId}/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDto createRoom(@PathVariable Long hotelId, @Valid @RequestBody CreateRoomRequestDto request) {
        return roomService.createRoom(hotelId, request);
    }
}