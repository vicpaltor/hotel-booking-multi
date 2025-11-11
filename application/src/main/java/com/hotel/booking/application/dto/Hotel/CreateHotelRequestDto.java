package com.hotel.booking.application.dto.Hotel;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

/**
 * @Value: Anotación de Lombok muy útil para DTOs. Crea una clase inmutable,
 *         lo que significa que una vez creado el objeto, sus valores no pueden cambiar.
 *         Automáticamente hace los campos 'private final' y genera getters,
 *         constructor, toString(), equals() y hashCode().
 *
 * @Builder: Permite crear el objeto de forma fluida.
 *
 * @NotBlank: Valida que el String no sea nulo y que no esté vacío (después de quitar espacios).
 * @Min / @Max: Valida que el número esté dentro de un rango.
 */

/**Este DTO recibirá los datos cuando un cliente quiera crear un nuevo hotel. **/


@Value
@Builder
public class CreateHotelRequestDto {


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
