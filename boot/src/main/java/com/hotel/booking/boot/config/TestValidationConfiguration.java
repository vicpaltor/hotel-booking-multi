package com.hotel.booking.boot.config;

import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ValidationAutoConfiguration.class)
public class TestValidationConfiguration {
    // Esta clase está vacía. Su único propósito es forzar la importación
    // de la configuración de validación completa de Spring Boot.
}