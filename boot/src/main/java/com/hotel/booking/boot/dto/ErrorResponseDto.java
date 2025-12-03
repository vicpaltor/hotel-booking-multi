package com.hotel.booking.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.Instant;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    private final int status;
    private final String error;
    private final String message;
    private final Instant timestamp;
}