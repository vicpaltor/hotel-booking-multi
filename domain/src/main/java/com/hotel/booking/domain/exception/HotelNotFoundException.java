package com.hotel.booking.domain.exception;

public class HotelNotFoundException extends RuntimeException{

    public HotelNotFoundException(String message) {
       super(message);// <-- Llama al constructor padre para establecer el mensaje
    }
    
}
