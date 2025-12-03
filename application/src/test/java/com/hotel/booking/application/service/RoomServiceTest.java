package com.hotel.booking.application.service;

import com.hotel.booking.application.dto.Hotel.HotelDto;
import com.hotel.booking.application.dto.Room.CreateRoomRequestDto;
import com.hotel.booking.application.dto.Room.RoomDto;
import com.hotel.booking.application.mapper.RoomMapperPort;
import com.hotel.booking.application.repository.HotelRepository;
import com.hotel.booking.application.repository.RoomRepository;
import com.hotel.booking.domain.exception.HotelNotFoundException;
import com.hotel.booking.domain.room.Room;
import com.hotel.booking.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private RoomMapperPort roomMapper;

    @InjectMocks
    private RoomService roomService;

    @Test
    void postCreateRoom_shouldCreateRoomSuccessfullyForExistingHotelId(){

        final Long existingHotelId = 1L;
        CreateRoomRequestDto request = CreateRoomRequestDto.builder()
                .roomNumber("101")
                .type(RoomType.DOUBLE)
                .pricePerNight(new BigDecimal("200.00"))
                .build();

        when(hotelRepository.existsById(existingHotelId)).thenReturn(true);

        Room roomEntity = new Room();
        when(roomMapper.toEntity(request)).thenReturn(roomEntity);

        roomEntity.setId(50L);
        roomEntity.setHotelId(existingHotelId);
        when(roomRepository.save(any(Room.class))).thenReturn(roomEntity);

        RoomDto expectedDto = RoomDto.builder().id(50L).hotelId(existingHotelId).build();
        when(roomMapper.toDto(roomEntity)).thenReturn(expectedDto);

        //WHEN

        RoomDto result = roomService.createRoom(existingHotelId, request);

        //THEN: Verificamos los resultados
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(50L);
        assertThat(result.getHotelId()).isEqualTo(existingHotelId);
        verify(roomRepository).save(any(Room.class));
    }

    @Test
    void shouldThrowHotelNotFoundExceptionWhenCreatingRoomForNonExistentHotel() {
        final Long nonExistentHotelId = 99L;
        CreateRoomRequestDto request = CreateRoomRequestDto.builder().build();

        when(hotelRepository.existsById(nonExistentHotelId)).thenReturn(false);

        // WHEN & THEN: Verificamos que se lanza la excepción correcta
        assertThrows(HotelNotFoundException.class, () -> {
            roomService.createRoom(nonExistentHotelId, request);
        });

        // Verificamos que NUNCA se intentó guardar la habitación
        verify(roomRepository, never()).save(any(Room.class));
    }


}
