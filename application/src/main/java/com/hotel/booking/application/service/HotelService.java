package com.hotel.booking.application.service;


import com.hotel.booking.application.dto.Hotel.CreateHotelRequestDto;
import com.hotel.booking.application.dto.Hotel.HotelDto;
import com.hotel.booking.application.dto.Hotel.UpdateHotelRequestDto;
import com.hotel.booking.application.mapper.HotelMapperPort;
import com.hotel.booking.application.repository.HotelRepository;
import com.hotel.booking.domain.exception.HotelNotFoundException;
import com.hotel.booking.domain.hotel.Hotel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HotelService {

    public HotelService(HotelMapperPort hotelMapper, HotelRepository hotelRepository) {
        this.hotelMapper = hotelMapper;
        this.hotelRepository = hotelRepository;
    }

    private final HotelMapperPort hotelMapper;

    private final HotelRepository hotelRepository;

    public HotelDto postCreateHotel(CreateHotelRequestDto request) {

        Hotel hotelEntity = hotelMapper.toEntity(request);

        Hotel savedHotelEntity = hotelRepository.save(hotelEntity);

        return hotelMapper.toDto(savedHotelEntity);

    }

    public HotelDto getHotelById(Long id){

    return hotelRepository.findById(id)
            .map(hotelMapper::toDto)
            .orElseThrow(()-> new HotelNotFoundException("Hotel no encontrado con id: "+ id));
    }

    public List<HotelDto> getAllHotels() {

        Iterable<Hotel> hotels = hotelRepository.findAll();

        return StreamSupport.stream(hotels.spliterator(), false)
                .map(hotelMapper::toDto)
                .collect(Collectors.toList());
    }

    public HotelDto updateHotel(Long idHotel, UpdateHotelRequestDto request) {
        return hotelRepository.findById(idHotel)
                .map(hotelToUpdate-> {
                    hotelToUpdate.setName(request.getName());
                    hotelToUpdate.setAddress(request.getAddress());
                    hotelToUpdate.setCity(request.getCity());
                    hotelToUpdate.setCountry(request.getCountry());
                    hotelToUpdate.setStars(request.getStars());
                    hotelToUpdate.setDescription(request.getDescription());

                    Hotel savedHotel = hotelRepository.save(hotelToUpdate);

                    return hotelMapper.toDto(savedHotel);

                })
                .orElseThrow(()-> new HotelNotFoundException("Hotel no encontrado con id: "+idHotel));
    }

    public void deleteHotel(Long hotelId) {

        if (!hotelRepository.existsById(hotelId)) {
            throw new HotelNotFoundException("Hotel no encontrado con id: "+hotelId);
        }

        hotelRepository.deleteById(hotelId);

    }
}
