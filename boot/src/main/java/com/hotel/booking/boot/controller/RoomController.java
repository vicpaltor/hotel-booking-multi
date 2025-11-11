package com.hotel.booking.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que es un controlador REST (devuelve JSON)
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    //private final RoomService roomService;

}
