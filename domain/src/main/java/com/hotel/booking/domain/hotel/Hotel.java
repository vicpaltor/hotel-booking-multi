package com.hotel.booking.domain.hotel;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @Data: Es una anotación de Lombok que agrupa @Getter, @Setter, @ToString,
 *        @EqualsAndHashCode y @RequiredArgsConstructor. ¡Todo en uno!
 *
 * @Builder: Implementa el patrón de diseño Builder para crear objetos de forma fluida.
 *           Ej: Hotel.builder().name("Hilton").stars(5).build();
 *
 * @NoArgsConstructor: Genera un constructor sin argumentos. Requerido por muchos frameworks.
 *
 * @AllArgsConstructor: Genera un constructor con un parámetro para cada campo de la clase.
 *
 * @Table("hotel"): Anotación de Spring Data JDBC que le dice a Spring que esta clase
 *                  se corresponde con la tabla llamada "hotel" en la base de datos.
 *
 * @Id: Anotación de Spring Data que marca el campo 'id' como la clave primaria de la tabla.
 */
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("hotel")
public class Hotel {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("address")
    private String address;
    @Column("city")
    private String city;
    @Column("country")
    private String country;
    @Column("stars")
    private Integer stars;
    @Column("description")
    private String description;
}
