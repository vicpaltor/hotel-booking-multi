package com.hotel.booking.boot.config;

import com.hotel.booking.application.mapper.HotelMapperPort;
import com.hotel.booking.application.repository.HotelRepository;
import com.hotel.booking.application.service.HotelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    // Le decimos a Spring: "Para crear un Bean de HotelService, usa este método".
    @Bean
    public HotelService hotelService(
            HotelRepository hotelRepository,
            HotelMapperPort hotelMapperPort
    ) {
        // Spring automáticamente buscará y nos pasará los beans que este método necesita.
        // Spring encontrará la implementación del HotelRepository (nuestro adaptador JdbcHotelRepository)
        // y la implementación del HotelMapperPort (nuestro adaptador HotelMapper de MapStruct).
        return new HotelService(hotelMapperPort, hotelRepository);
    }

    // Repite el patrón para otros servicios...
    /*
    @Bean
    public RoomService roomService(
            RoomRepository roomRepository,
            RoomMapperPort roomMapperPort
    ) {
        return new RoomService(roomMapperPort, roomRepository);
    }
    */
}
