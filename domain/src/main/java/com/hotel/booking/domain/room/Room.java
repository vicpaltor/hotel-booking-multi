package com.hotel.booking.domain.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("room")
public class Room {

    @Id
    @Column("id")
    private Long id;

    @Column("room_number")
    private String roomNumber;

    @Column("type")
    private RoomType type;

    @Column("price_per_night")
    private BigDecimal pricePerNight;

    @Column("hotel_id")
    private Long hotelId;

}
