package com.hotel.booking.application.dto.Hotel;

/*
    Este DTO tendrá la estructura de datos que devolveremos al cliente cuando nos pida información de un hotel.
    En este caso, es muy parecido a la entidad, pero incluye el id.*/

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HotelDto {

    Long id;
    String name;
    String address;
    String city;
    String country;
    Integer stars;
    String description;

}
