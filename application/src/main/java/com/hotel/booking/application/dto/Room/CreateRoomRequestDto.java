package com.hotel.booking.application.dto.Room;


import com.hotel.booking.domain.room.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CreateRoomRequestDto {

    @NotBlank(message = "El numero de la habitacion no puede estar vacío")
    private String roomNumber;

    @NotBlank(message = "El tipo no puede estar vacío")
    private RoomType type;

    @NotNull(message = "El precio por noche no puede ser nulo")
    @Positive(message = "El precio por noche debe ser mayor que cero")
    private BigDecimal pricePerNight;
}
