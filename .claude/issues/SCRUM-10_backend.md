# [SCRUM-10] Implementación de Sistema de Reservas de Habitaciones

## Descripción
Implementar el módulo de gestión de reservas de habitaciones del hotel, permitiendo a los usuarios buscar disponibilidad, crear reservas, modificarlas y cancelarlas. El sistema debe manejar conflictos de reservas y validar reglas de negocio como fechas mínimas de estancia y políticas de cancelación.

## Requisitos Funcionales
- [ ] Buscar habitaciones disponibles por rango de fechas y tipo de habitación
- [ ] Crear una nueva reserva con validación de disponibilidad
- [ ] Modificar una reserva existente
- [ ] Cancelar una reserva con aplicación de políticas
- [ ] Consultar historial de reservas por usuario
- [ ] Notificar al usuario sobre cambios en su reserva

## Requisitos Técnicos
- **Stack**: Java 17, Spring Boot 3.x
- **Arquitectura**: Hexagonal (Ports & Adapters)
- **Base de datos**: PostgreSQL
- **Mensajería**: Para notificaciones (opcional: RabbitMQ/Kafka)
- **Validación**: Bean Validation
- **Testing**: JUnit 5, Mockito, Testcontainers

## Modelo de Dominio

### Agregados Principales
1. **Booking** (Reserva) - Aggregate Root
   - BookingId (Value Object)
   - Customer (Value Object/Entity Reference)
   - Room (Entity Reference)
   - BookingPeriod (Value Object: checkIn, checkOut)
   - BookingStatus (Value Object: PENDING, CONFIRMED, CANCELLED)
   - PaymentStatus (Value Object)
   - CancellationPolicy (Value Object)

2. **Room** (Habitación)
   - RoomId
   - RoomType (SINGLE, DOUBLE, SUITE)
   - RoomStatus (AVAILABLE, OCCUPIED, MAINTENANCE)
   - PricePerNight

### Domain Services
- **AvailabilityService**: Verificar disponibilidad de habitaciones
- **PricingService**: Calcular precio total de reserva
- **CancellationPolicyService**: Aplicar políticas de cancelación

### Domain Events
- `BookingCreated`
- `BookingConfirmed`
- `BookingModified`
- `BookingCancelled`

## Casos de Uso (Application Layer)

1. **CreateBookingUseCase**
   - Input: CreateBookingCommand (customerId, roomId, checkIn, checkOut)
   - Output: BookingId
   - Validaciones: Disponibilidad, fechas válidas, estancia mínima

2. **ModifyBookingUseCase**
   - Input: ModifyBookingCommand (bookingId, newCheckIn, newCheckOut)
   - Output: Updated Booking
   - Validaciones: Permisos, disponibilidad nueva fecha

3. **CancelBookingUseCase**
   - Input: CancelBookingCommand (bookingId, reason)
   - Output: CancellationConfirmation
   - Validaciones: Política de cancelación, plazos

4. **SearchAvailableRoomsUseCase**
   - Input: SearchCriteria (checkIn, checkOut, roomType)
   - Output: List<AvailableRoom>

## Puertos (Interfaces)

### Puertos Primarios (Driving/Inbound)
```java
// application/ports/in
- CreateBookingPort
- ModifyBookingPort
- CancelBookingPort
- SearchAvailabilityPort
```

### Puertos Secundarios (Driven/Outbound)
```java
// application/ports/out
- BookingRepositoryPort
- RoomRepositoryPort
- NotificationPort
- PaymentPort
- EventPublisherPort
```

## Adaptadores (Infrastructure)

### Adaptadores Primarios
- **REST API Controller**: `/api/bookings`
  - POST /bookings - Crear reserva
  - PUT /bookings/{id} - Modificar reserva
  - DELETE /bookings/{id} - Cancelar reserva
  - GET /bookings/search - Buscar disponibilidad

### Adaptadores Secundarios
- **JPA Repository Adapter**: Implementación con Spring Data JPA
- **Email Notification Adapter**: Envío de emails
- **Payment Gateway Adapter**: Integración con pasarela de pago
- **Event Publisher Adapter**: Publicación de domain events

## Criterios de Aceptación

### AC-1: Crear Reserva
**Dado** un usuario autenticado y una habitación disponible  
**Cuando** el usuario solicita crear una reserva con fechas válidas  
**Entonces** el sistema debe:
- Validar que la habitación esté disponible en ese período
- Crear la reserva con estado PENDING
- Generar un BookingId único
- Publicar el evento BookingCreated
- Retornar confirmación con los detalles de la reserva

### AC-2: Validación de Conflictos
**Dado** una habitación ya reservada del 1 al 5 de junio  
**Cuando** otro usuario intenta reservar la misma habitación del 3 al 7 de junio  
**Entonces** el sistema debe rechazar la reserva con mensaje de error "Habitación no disponible en las fechas seleccionadas"

### AC-3: Cancelación con Política
**Dado** una reserva confirmada con política de cancelación de 48 horas  
**Cuando** el usuario cancela con 72 horas de anticipación  
**Entonces** el sistema debe:
- Cambiar estado a CANCELLED
- Aplicar reembolso completo
- Liberar la habitación
- Enviar notificación de cancelación

**Dado** una reserva confirmada con política de cancelación de 48 horas  
**Cuando** el usuario cancela con 24 horas de anticipación  
**Entonces** el sistema debe:
- Cambiar estado a CANCELLED
- Aplicar penalización (50% reembolso)
- Liberar la habitación
- Enviar notificación de cancelación con penalización

### AC-4: Búsqueda de Disponibilidad
**Dado** habitaciones de diferentes tipos en el sistema  
**Cuando** un usuario busca habitaciones disponibles del 10 al 15 de julio para tipo DOUBLE  
**Entonces** el sistema debe retornar solo habitaciones tipo DOUBLE disponibles en todo ese período

## Reglas de Negocio

1. **Estancia mínima**: 1 noche
2. **Estancia máxima**: 30 noches
3. **Anticipación mínima**: 1 día desde la fecha actual
4. **Política de cancelación por defecto**: 48 horas antes del check-in sin penalización
5. **Check-in**: 15:00, Check-out: 12:00
6. **Una habitación no puede tener reservas solapadas**
7. **El check-out debe ser posterior al check-in**

## Estructura de Directorios Esperada

```
domain/
├── model/
│   ├── aggregates/
│   │   ├── Booking.java
│   │   └── Room.java
│   ├── valueobjects/
│   │   ├── BookingId.java
│   │   ├── BookingPeriod.java
│   │   ├── BookingStatus.java
│   │   └── RoomType.java
│   ├── entities/
│   └── events/
│       ├── BookingCreated.java
│       ├── BookingConfirmed.java
│       └── BookingCancelled.java
├── services/
│   ├── AvailabilityService.java
│   ├── PricingService.java
│   └── CancellationPolicyService.java
└── exceptions/
    ├── RoomNotAvailableException.java
    └── InvalidBookingPeriodException.java

application/
├── usecases/
│   ├── CreateBookingUseCase.java
│   ├── ModifyBookingUseCase.java
│   ├── CancelBookingUseCase.java
│   └── SearchAvailableRoomsUseCase.java
├── ports/
│   ├── in/
│   │   ├── CreateBookingPort.java
│   │   ├── ModifyBookingPort.java
│   │   ├── CancelBookingPort.java
│   │   └── SearchAvailabilityPort.java
│   └── out/
│       ├── BookingRepositoryPort.java
│       ├── RoomRepositoryPort.java
│       ├── NotificationPort.java
│       └── EventPublisherPort.java
└── dto/
    ├── CreateBookingCommand.java
    ├── BookingResponse.java
    └── AvailableRoomDTO.java

infrastructure/
├── adapters/
│   ├── in/
│   │   └── rest/
│   │       └── BookingController.java
│   └── out/
│       ├── persistence/
│       │   ├── BookingJpaRepository.java
│       │   ├── BookingEntity.java
│       │   ├── BookingRepositoryAdapter.java
│       │   └── BookingMapper.java
│       ├── messaging/
│       │   └── EventPublisherAdapter.java
│       └── notification/
│           └── EmailNotificationAdapter.java
└── config/
    └── BookingConfiguration.java
```

## Consideraciones de Testing

- **Unit Tests**: Para entidades, value objects y domain services
- **Integration Tests**: Para use cases con mocks de puertos
- **Architecture Tests**: Con ArchUnit para validar dependencias
- **Testcontainers**: Para tests de repositorio con PostgreSQL real
- **Contract Tests**: Para validar adaptadores REST

## Dependencias Maven

```xml
<!-- Spring Boot -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- Database -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>

<!-- Testing -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
```

## Notas Adicionales

- Considerar implementar patrón CQRS si las consultas se vuelven complejas
- Evaluar uso de Saga pattern para gestionar transacciones distribuidas con pagos
- Implementar rate limiting en el API para prevenir abusos
- Considerar cache para búsquedas frecuentes de disponibilidad
- Implementar auditoría de cambios en reservas

## Prioridad
**Alta** - Módulo crítico para el funcionamiento del sistema

## Estimación
8-10 Story Points
