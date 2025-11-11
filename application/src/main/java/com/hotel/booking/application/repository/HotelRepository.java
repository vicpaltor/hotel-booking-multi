package com.hotel.booking.application.repository;


import com.hotel.booking.domain.hotel.Hotel;
import java.util.Optional;

public interface HotelRepository {
    Hotel save(Hotel hotel);
    Optional<Hotel> findById(Long id);
    Iterable<Hotel> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);

}
