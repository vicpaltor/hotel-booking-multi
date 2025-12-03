package com.hotel.booking.boot.controller;

import com.hotel.booking.application.service.HotelService; // Asegúrate de que importas la clase, no la interfaz
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.booking.application.dto.Hotel.CreateHotelRequestDto;
import com.hotel.booking.application.dto.Hotel.HotelDto;
import com.hotel.booking.application.dto.Hotel.UpdateHotelRequestDto;
import com.hotel.booking.domain.exception.HotelNotFoundException;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getHotelById_shouldReturnHotelByIdAndStatus200WhenFound() throws Exception{
        //GIVE
        final Long hotelId = 1L;

        HotelDto hotelDto = HotelDto.builder()
                .id(hotelId)
                .name("Hotel Encontrado")
                .city("TestVille")
                .build();

        // Configuramos el mock del servicio para que devuelva nuestro DTO de prueba
        when(hotelService.getHotelById(hotelId)).thenReturn(hotelDto);

        //WHEN & //THEN

        mockMvc.perform(get("/api/hotels")
                        .param("id",hotelId.toString()))
                .andExpect(status().isOk()) // Esperamos un estado 200 OK
                .andExpect(jsonPath("$.id", is(1))) // Verificamos el contenido del JSON
                .andExpect(jsonPath("$.name", is("Hotel Encontrado")));
    }

    @Test
    void getHotelById_shouldReturnStatus404WhenNotFound() throws Exception {
        // GIVEN
        final Long nonExistentId = 99L;
        when(hotelService.getHotelById(nonExistentId))
                .thenThrow(new HotelNotFoundException("Hotel no encontrado con id: " + nonExistentId));

        // WHEN & THEN
        mockMvc.perform(get("/api/hotels").param("id", nonExistentId.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getFindHotels_shouldReturnAllHotelsWhenNoIdParamIsPresent() throws Exception {
        // GIVEN: Preparamos una lista de DTOs que el servicio devolverá.
        HotelDto dto1 = HotelDto.builder()
                .id(1L)
                .name("Hotel Uno")
                .build();

        HotelDto dto2 = HotelDto.builder()
                .id(2L)
                .name("Hotel Dos")
                .build();

        List<HotelDto> hotelList = Arrays.asList(dto1, dto2);

        // Configuramos el mock del servicio.
        when(hotelService.getAllHotels()).thenReturn(hotelList);

        // WHEN & THEN
        mockMvc.perform(get("/api/hotels")) // Petición sin parámetros
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))) // Verificamos que la respuesta es un array de tamaño 2
                .andExpect(jsonPath("$[0].name", is("Hotel Uno"))); // Verificamos el primer elemento
    }

    @Test
    void postCreateHotel_shouldPostCreateHotelAndReturn201() throws Exception {

        CreateHotelRequestDto requestDto = CreateHotelRequestDto.builder()
                .name("Hotel API Test")
                .city("Testville")
                .country("Testland")
                .address("123 Test Street")
                .stars(4)
                .build();

        HotelDto responseDto = HotelDto.builder()
                .id(1L)
                .name("Hotel API Test")
                .city("Testville")
                .build();

        // Configuramos el mock del servicio. Cuando se llame a createHotel...

        when(hotelService.postCreateHotel(any(CreateHotelRequestDto.class)))
                .thenReturn(responseDto);

        // WHEN & THEN (Realizar la petición y verificar la respuesta)

        mockMvc.perform(post("/api/hotels")// Simula un POST a /api/hotels
                .contentType(MediaType.APPLICATION_JSON)// Le decimos que el cuerpo es JSON
                .content(objectMapper.writeValueAsString(requestDto))) // Convertimos el request a JSON

                .andExpect(status().isCreated()) // Esperamos un estado HTTP 201 (Created)
                .andExpect(jsonPath("$.id", is(1))) // Verificamos el JSON de respuesta
                .andExpect(jsonPath("$.name", is("Hotel API Test")));
    }

    @Test
    void postCreateHotel_shouldReturn400WhenHotelDataIsInvalid() throws Exception {

        CreateHotelRequestDto invalidRequest = CreateHotelRequestDto.builder()
                // Vacío (viola @NotBlank)
                .name("")
                .city("")
                .country("")
                .address("")
                // Mayor a 5 (viola @Max(5))
                .stars(6)
                .build();

        mockMvc.perform(post("/api/hotels")// Simula un POST a /api/hotels
                .contentType(MediaType.APPLICATION_JSON)// Le decimos que el cuerpo es JSON
                .content(objectMapper.writeValueAsString(invalidRequest))) // Convertimos el request a JSON
                .andExpect(status().isBadRequest()); // 400 Bad Request

    }


    @Test
    void updateHotel_shouldUpdateHotelAndReturn200() throws Exception {
        // GIVEN: El ID y los DTOs de petición y respuesta.
        // Los datos son consistentes entre la petición y la respuesta esperada.
        final Long hotelId = 1L;
        UpdateHotelRequestDto requestDto = UpdateHotelRequestDto.builder()
                .name("Hotel Actualizado")
                .address("Calle Falsa 123, Actualizada")
                .city("Ciudad Actualizada")
                .country("País Actualizado")
                .stars(5)
                .description("Descripción actualizada.")
                .build();

        HotelDto responseDto = HotelDto.builder()
                .id(hotelId)
                .name("Hotel Actualizado")
                .address("Calle Falsa 123, Actualizada")
                .city("Ciudad Actualizada")
                .country("País Actualizado")
                .stars(5)
                .description("Descripción actualizada.")
                .build();

        // Configuramos el mock del servicio. Cuando se llame a updateHotel...

        when(hotelService.updateHotel(eq(1L), any(UpdateHotelRequestDto.class)))
                .thenReturn(responseDto);

        // WHEN & THEN (Realizar la petición y verificar la respuesta)

        mockMvc.perform(put("/api/hotels/{id}", hotelId)
                        .contentType(MediaType.APPLICATION_JSON)// Le decimos que el cuerpo es JSON
                        .content(objectMapper.writeValueAsString(requestDto))) // Convertimos el request a JSON

                .andExpect(status().isOk()) // Esperamos un estado HTTP 200 (Update)
                .andExpect(jsonPath("$.id", is(hotelId.intValue())))
                .andExpect(jsonPath("$.name", is("Hotel Actualizado")))
                .andExpect(jsonPath("$.city", is("Ciudad Actualizada")));

    }

    @Test
    void deleteHotel_shouldDeleteHotelAndReturn204NoContent() throws Exception{
        //GIVE
        final Long DeleteId = 1L;

        doNothing().when(hotelService).deleteHotel(DeleteId);

        //WHEN & THEN

        mockMvc.perform(delete("/api/hotels/{id}",DeleteId))
                .andExpect(status().isNoContent());

    }


}
