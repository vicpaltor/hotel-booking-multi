package com.hotel.booking.infrastructure.repository.jdbc;

import com.hotel.booking.domain.hotel.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.hotel.booking.application.repository.HotelRepository;

import java.util.Optional;

@Component
public class JdbcHotelRepository implements HotelRepository {

    private final SpringHotelRepository repository; // La implementación real de Spring Data

    public JdbcHotelRepository(SpringHotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Hotel save(Hotel hotel) { return repository.save(hotel); }

    @Override
    public Optional<Hotel> findById(Long id) { return repository.findById(id); }

    @Override
    public Iterable<Hotel> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
