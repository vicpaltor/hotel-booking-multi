package com.hotel.booking.application.dto.Room;

import com.hotel.booking.domain.room.RoomType;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class RoomDto {

    Long id;
    String roomNumber;
    RoomType type;
    BigDecimal pricePerNight;
    Long hotelId; // Incluimos el ID del hotel en la respuesta
}
