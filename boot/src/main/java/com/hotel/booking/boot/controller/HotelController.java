package com.hotel.booking.boot.controller;

import com.hotel.booking.application.dto.Hotel.CreateHotelRequestDto;
import com.hotel.booking.application.dto.Hotel.HotelDto;
import com.hotel.booking.application.dto.Hotel.UpdateHotelRequestDto;
import com.hotel.booking.application.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // Indica que es un controlador REST (devuelve JSON)
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/{id}")
    public HotelDto getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping
    //Usaremos ResponseEntity porque nos da un control total sobre la respuesta, permitiéndonos devolver a veces un solo objeto y a veces una lista.
    public ResponseEntity<?> getFindHotels(@RequestParam(required = false) Long id){
        // @RequestParam(required = false) le dice a Spring que el parámetro 'id' es opcional.
        // Si no viene en la URL, su valor será 'null'.

        if (id != null) {
            // Si el cliente proporcionó un ID (ej: /api/hotels?id=1)
            HotelDto hotel = hotelService.getHotelById(id);
            return ResponseEntity.ok(hotel); // Devuelve un solo hotel
        } else {
            // Si el cliente NO proporcionó un ID (ej: /api/hotels)
            List<HotelDto> hotels = hotelService.getAllHotels();
            return ResponseEntity.ok(hotels); // Devuelve una lista de hoteles
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelDto postCreateHotel(@Valid @RequestBody CreateHotelRequestDto requestDto){

        // @RequestBody: Convierte el cuerpo JSON de la petición a un objeto CreateHotelRequest
        // @Valid: Activa las validaciones que definimos en el DTO (@NotBlank, etc.)
        return hotelService.postCreateHotel(requestDto);
    }

    @PutMapping("/{id}")
    public HotelDto updateHotel(@PathVariable Long id, @Valid @RequestBody UpdateHotelRequestDto requestDto){
        return hotelService.updateHotel(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
    }





}
