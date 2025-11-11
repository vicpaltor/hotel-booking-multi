package com.hotel.booking.infrastructure.repository.jdbc;

import com.hotel.booking.domain.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringHotelRepository extends CrudRepository<Hotel, Long> {
}
