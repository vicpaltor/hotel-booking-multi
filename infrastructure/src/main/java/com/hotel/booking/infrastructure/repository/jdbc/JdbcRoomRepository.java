package com.hotel.booking.infrastructure.repository.jdbc;

import com.hotel.booking.application.repository.RoomRepository;
import com.hotel.booking.domain.room.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcRoomRepository implements RoomRepository {

    private final SpringRoomRepository repository;

    public JdbcRoomRepository(SpringRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public Room save(Room room) {
        return repository.save(room);
    }

    @Override
    public Optional<Room> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Room> findByHotelId(Long hotelId) {
        return repository.findByHotelId(hotelId);
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