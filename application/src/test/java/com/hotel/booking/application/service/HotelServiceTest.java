package com.hotel.booking.application.service;


import com.hotel.booking.application.dto.Hotel.CreateHotelRequestDto;
import com.hotel.booking.application.dto.Hotel.HotelDto;
import com.hotel.booking.application.dto.Hotel.UpdateHotelRequestDto;
import com.hotel.booking.application.mapper.HotelMapperPort;
import com.hotel.booking.application.repository.HotelRepository;
import com.hotel.booking.domain.exception.HotelNotFoundException;
import com.hotel.booking.domain.hotel.Hotel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

// Le decimos a JUnit 5 que use la extensión de Mockito para gestionar los mocks
@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    // @Mock: Crea un mock (un doble de prueba) de esta dependencia.
    // No usaremos el repositorio real, sino uno falso que controlamos.
    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapperPort hotelMapper;

    // @InjectMocks: Crea una instancia de HotelService e intenta inyectar
    // los mocks declarados en esta clase (hotelRepository y hotelMapper).
    @InjectMocks
    private HotelService hotelService;

    @Test
    void postCreateHotel_shouldPostCreateHotelSuccessfully(){
        // GIVE (Dados unos datos de entrada y el comportamiento de los mocks)

        CreateHotelRequestDto request = CreateHotelRequestDto.builder()
                .name("Hotel Económico")
                .address("Avenida Siempre Viva 742")
                .city("Shelbyville")
                .country("USA")
                .stars(3)
                .description("Bueno, bonito y barato.")
                .build();

        // Entidad que simula la conversión
        // Simula la conversión del request a entidad
        Hotel hotelEntity = Hotel.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .stars(request.getStars())
                .description(request.getDescription())
                .build();

        // Simula la entidad guardada con ID
        Hotel savedHotelEntity = Hotel.builder()
                .id(1L)
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .stars(request.getStars())
                .description(request.getDescription())
                .build();

        // DTO de respuesta esperado
        HotelDto expectedDto = HotelDto.builder()
                .id(1L)
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .country(request.getCountry())
                .stars(request.getStars())
                .description(request.getDescription())
                .build();

        /** Configuramos los mocks:**/
        // 1. Cuando se llame a hotelMapper.toEntity con CUALQUIER request...
        when(hotelMapper.toEntity(request)).thenReturn(hotelEntity);

        // 2. Cuando se llame a hotelRepository.save con CUALQUIER entidad Hotel...
        when(hotelRepository.save(hotelEntity)).thenReturn(savedHotelEntity);

        // 3. Cuando se llame a hotelMapper.toDto con la entidad guardada...
        when(hotelMapper.toDto(savedHotelEntity)).thenReturn(expectedDto);

        // WHEN (Cuando ejecutamos el método que queremos probar)
        // Esta línea dará un error de compilación porque el método no existe aún.
        // ¡Eso es TDD!
        HotelDto result = hotelService.postCreateHotel(request);

        // THEN (Entonces verificamos que el resultado es el esperado)
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Hotel Económico");
        assertThat(result.getCity()).isEqualTo("Shelbyville");
        assertThat(result.getStars()).isEqualTo(3);
    }
    @Test
    void getHotelById_shouldReturnHotelWhenFound(){
         // GIVE (Dados unos datos de entrada)
        Hotel request = Hotel.builder()
                .name("Hotel Económico")
                .address("Avenida Siempre Viva 742")
                .city("Shelbyville")
                .country("USA")
                .stars(3)
                .description("Bueno, bonito y barato.")
                .build();
        HotelDto requestDto = HotelDto.builder()
                .id(1L)
                .name("Hotel Económico")
                .address("Avenida Siempre Viva 742")
                .city("Shelbyville")
                .country("USA")
                .stars(3)
                .description("Bueno, bonito y barato.")
                .build();

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(request));
        when(hotelMapper.toDto(request)).thenReturn(requestDto);

         // WHEN (Cuando ejecutamos el método que queremos probar)
        HotelDto result = hotelService.getHotelById(1L);

         // THEN (Entonces verificamos que el resultado es el esperado)
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Hotel Económico");
        assertThat(result.getCity()).isEqualTo("Shelbyville");
        assertThat(result.getCountry()).isEqualTo("USA");
        assertThat(result.getStars()).isEqualTo(3);
        assertThat(result.getDescription()).isEqualTo("Bueno, bonito y barato.");

    }
    @Test
    void getHotelById_shouldThrowNotFoundExceptionWhenHotelDoesNotExist(){
        // GIVE (Dados unos datos de entrada)
        final Long nonExistentId = 99L;

        when(hotelRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // WHEN & THEN (Cuando llamamos al método y verificamos que lanza la excepción)

        // assertThrows espera dos argumentos:
        // 1. La clase de la excepción que esperamos que se lance (HotelNotFoundException.class).
        // 2. Una expresión lambda que contiene el código que DEBE lanzar la excepción.
//        HotelNotFoundException exception =
        assertThrows(
                HotelNotFoundException.class,
                () -> {hotelService.getHotelById(nonExistentId);
        });

        // Verificamos que el mensaje de la excepción capturada es el correcto.
//        assertThat(exception.getMessage()).isEqualTo("Hotel no encontrado con id: " + nonExistentId);

        // Verificamos que el método toDto del mapper NUNCA fue llamado,
        // porque la lógica debería haber terminado antes al lanzar la excepción.
        verify(hotelMapper, never()).toDto(any(Hotel.class));
    }
    @Test
    void getAllHotels_shouldReturnAllHotelWhenFound(){
        //GIVE
        Hotel hotel1 = Hotel.builder().id(1L).name("Hotel Paraíso_1").build();
        Hotel hotel2 = Hotel.builder().id(2L).name("Hotel Paraíso_2").build();
        List<Hotel> hotelListFromRepo = Arrays.asList(hotel1,hotel2);

        HotelDto hotelDto1 = HotelDto.builder().id(1L).name("Hotel Paraíso_1").build();
        HotelDto hotelDto2 = HotelDto.builder().id(2L).name("Hotel Paraíso_2").build();

        // Configuramos el mock del repositorio
        when(hotelRepository.findAll()).thenReturn(hotelListFromRepo);

        // Configuramos el mock del mapper para cada conversión
        when(hotelMapper.toDto(hotel1)).thenReturn(hotelDto1);
        when(hotelMapper.toDto(hotel2)).thenReturn(hotelDto2);

        //WHEN
        List<HotelDto> result = hotelService.getAllHotels();

        //THEN

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2); // Verificamos que la lista tiene el tamaño correcto

        // Verificamos que se llamó al repositorio una vez
        verify(hotelRepository).findAll();
    }
    @Test
    void getAllHotels_shouldThrowNotFoundExceptionWhenAnyHotelExist(){
        //GIVE
        when(hotelRepository.findAll()).thenReturn(Collections.emptyList());

        //WHEN
        List<HotelDto> result = hotelService.getAllHotels();

        //THEN
        assertThat(result).isNotNull();

        // Verificamos que no se intentó hacer ninguna conversión, ya que no había elementos
        verify(hotelMapper, never()).toDto(any(Hotel.class));
        assertThat(result).isEmpty(); // La aserción clave es verificar que está vacía

    }
    @Test
    void putUpdateHotelRequest_shouldUpdateHotelSuccessfully(){
        // GIVEN: Preparamos todos los objetos que necesitaremos en el test.

        // 1. El hotel como existe AHORA en la base de datos.
        Hotel a = Hotel.builder()
                .id(1L) // El ID es crucial para la búsqueda
                .name("Hotel Viejo")
                .stars(3)
                .build();

        // 2. El DTO que llega desde el controlador con los datos para actualizar.
        UpdateHotelRequestDto b = UpdateHotelRequestDto.builder()
                .name("Hotel Actualizado")
                .stars(5)
                .build();

        // 3. El estado final que esperamos que tenga la entidad ANTES de devolverla.
        Hotel c = Hotel.builder()
                .id(1L)
                .name("Hotel Actualizado")
                .stars(5)
                .build();

        // 4. El DTO final que el servicio debe devolver al controlador.
        HotelDto d = HotelDto.builder()
                .id(1L)
                .name("Hotel Actualizado")
                .stars(5)
                .build();

        // --- Configuración de los Mocks ---

        when(hotelRepository.findById(1L)).thenReturn(Optional.of((a)));

        when(hotelRepository.save(any(Hotel.class))).thenReturn(c);

        when(hotelMapper.toDto(c)).thenReturn(d);

        // WHEN: Ejecutamos el metodo que queremos probar.

        HotelDto result = hotelService.updateHotel(1L, b);

        // THEN: Verificamos que tódo ha ocurrido como esperábamos.
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Hotel Actualizado");
        assertThat(result.getStars()).isEqualTo(5);

        // Verificamos que el metodo save() del repositorio fue llamado exactamente una vez.
        verify(hotelRepository).save(any(Hotel.class));
    }
    @Test
    void putShouldThrowNotFoundExceptionWhenUpdatingNonExistentHotel(){

        // GIVEN: Preparamos todos los objetos que necesitaremos en el test.

        final Long nonExistentId = 99L;

        // 2. El DTO que llega desde el controlador con los datos para actualizar.
        UpdateHotelRequestDto b = UpdateHotelRequestDto.builder()
                .name("Hotel Fantasma")
                .stars(5)
                .build();


        // --- Configuración de los Mocks ---

        // WHEN & THEN: Verificamos que la llamada al servicio lanza la excepción esperada.
        assertThrows(HotelNotFoundException.class, () -> {
            // Esta es la llamada que debe fallar
            hotelService.updateHotel(nonExistentId, b);
        });

        // Opcional pero recomendado: Verificamos que NUNCA se intentó guardar nada,
        // ya que la ejecución debería haberse detenido antes.
        verify(hotelRepository, never()).save(any(Hotel.class));

    }

    @Test
    void deleteHotel_shouldDeleteHotelSuccessfully(){
        //GIVE
        final Long existingId = 1L;

        when(hotelRepository.existsById(existingId)).thenReturn(true);


        //WHEN

        hotelService.deleteHotel(existingId);

        //THEN
        verify(hotelRepository, times(1)).deleteById(existingId);
    }

    @Test
    void deleteHotel_shouldThrowNotFoundExceptionWhenDeletingNonExistentHotel(){

        //GIVEN
        final Long nonexistingId = 99L;

        when(hotelRepository.existsById(nonexistingId)).thenReturn(false);

        //WHEN & THEN:

        assertThrows(HotelNotFoundException.class,() -> {
            hotelService.deleteHotel(nonexistingId);
        });

        verify(hotelRepository,never()).deleteById(anyLong());

    }

}
