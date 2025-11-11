package com.hotel.booking.application.mapper;

import com.hotel.booking.application.dto.Hotel.CreateHotelRequestDto;
import com.hotel.booking.application.dto.Hotel.HotelDto;
import com.hotel.booking.application.dto.Hotel.UpdateHotelRequestDto;
import com.hotel.booking.domain.hotel.Hotel;

public interface HotelMapperPort {

    /**
     * Convierte una entidad Hotel a un HotelDto.
     * MapStruct es inteligente: como los campos en ambas clases se llaman igual
     * (name, address, city, etc.), sabe cómo mapearlos automáticamente.
     * @param hotel La entidad de dominio a convertir.
     * @return El DTO correspondiente.
     */
    HotelDto toDto(Hotel hotel);
    /**
     * Convierte un DTO de creación (CreateHotelRequest) a una entidad Hotel.
     * De nuevo, los nombres de campo coinciden, por lo que el mapeo es automático.
     * @param request El DTO con los datos de entrada.
     * @return La entidad de dominio lista para ser guardada.
     */
    Hotel toEntity(CreateHotelRequestDto request);
    UpdateHotelRequestDto toUpdateDto(Hotel request);
}
