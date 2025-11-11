package com.hotel.booking.application.dto.Room;

import com.hotel.booking.domain.room.RoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateRoomRequestDto {

    @NotBlank(message = "El numero de la habitacion no puede estar vacío")
    private String roomNumber;

    @NotBlank(message = "El tipo no puede estar vacío")
    private RoomType type;

    @Min(value = 0, message = "El precio de la noche deben ser como mínimo 0")
    private Double pricePerNight;
}
