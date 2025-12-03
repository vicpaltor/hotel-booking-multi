package com.hotel.booking.boot.mapper;

import com.hotel.booking.application.mapper.HotelMapperPort;
import com.hotel.booking.domain.hotel.Hotel;
import com.hotel.booking.application.dto.Hotel.CreateHotelRequestDto;
import com.hotel.booking.application.dto.Hotel.HotelDto;
import com.hotel.booking.application.dto.Hotel.UpdateHotelRequestDto;
import org.mapstruct.Mapper;

/**
 * @Mapper(componentModel = "spring"): Esta es la anotación principal de MapStruct.
 *   - Le indica a MapStruct que procese esta interfaz.
 *   - 'componentModel = "spring"' es la instrucción clave: le dice a MapStruct que
 *     genere una implementación de esta interfaz que sea un Bean de Spring.
 *     Esto nos permitirá inyectarla (@Autowired) en otras clases como los servicios.
 */

@Mapper(componentModel = "spring")
public interface HotelMapper extends HotelMapperPort {
    // Al extender la interfaz del puerto, MapStruct sabe que tiene que
    // implementar los métodos toDto y toEntity automáticamente.
}