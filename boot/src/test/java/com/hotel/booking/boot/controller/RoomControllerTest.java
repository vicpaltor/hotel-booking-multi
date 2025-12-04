package com.hotel.booking.boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.booking.application.dto.Room.CreateRoomRequestDto;
import com.hotel.booking.application.dto.Room.RoomDto;
import com.hotel.booking.application.service.RoomService;
import com.hotel.booking.boot.config.TestValidationConfiguration;
import com.hotel.booking.domain.room.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestValidationConfiguration.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RoomController roomController;

    @MockBean
    private RoomService roomService;

    @Test
    void contextLoads() {
        assertThat(roomController).isNotNull();
    }

}