package com.hotel.booking.boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.booking.application.dto.Room.CreateRoomRequestDto;
import com.hotel.booking.application.dto.Room.RoomDto;
import com.hotel.booking.application.service.RoomService;
import com.hotel.booking.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Inyectamos el controlador real para el test 'contextLoads'
    @Autowired
    private RoomController roomController;

    @MockBean
    private RoomService roomService;

    @Test
    void contextLoads() {
        assertThat(roomController).isNotNull();
    }

    @Test
    void shouldCreateRoomAndReturn201() throws Exception {
        // GIVEN: Un ID de hotel y los datos de la nueva habitación.
        final Long hotelId = 1L;
        CreateRoomRequestDto request = CreateRoomRequestDto.builder()
                .roomNumber("Suite Presidencial")
                .type(RoomType.SUITE)
                .pricePerNight(new BigDecimal("500.00"))
                .build();

        // Esto es lo que esperamos que el servicio devuelva.
        RoomDto responseDto = RoomDto.builder()
                .id(10L) // Un ID de habitación de ejemplo
                .hotelId(hotelId)
                .roomNumber("Suite Presidencial")
                .type(RoomType.SUITE)
                .pricePerNight(new BigDecimal("500.00"))
                .build();

        // Configuramos el mock del servicio para que devuelva nuestro DTO de respuesta.
        when(roomService.createRoom(eq(hotelId), any(CreateRoomRequestDto.class)))
                .thenReturn(responseDto);

        // WHEN & THEN: Simulamos la petición POST y verificamos la respuesta.
        mockMvc.perform(post("/api/hotels/{hotelId}/rooms", hotelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.hotelId", is(hotelId.intValue())))
                .andExpect(jsonPath("$.roomNumber", is("Suite Presidencial")));
    }
}