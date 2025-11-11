package com.hotel.booking.application.dto.Hotel;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateHotelRequestDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    String name;

    @NotBlank(message = "La dirección no puede estar vacía")
    String address;

    @NotBlank(message = "La ciudad no puede estar vacía")
    String city;

    @NotBlank(message = "El país no puede estar vacío")
    String country;

    @Min(value = 1, message = "Las estrellas deben ser como mínimo 1")
    @Max(value = 5, message = "Las estrellas deben ser como máximo 5")
    Integer stars;

    String description;
}
