package com.example.uber_backend.Transformer;

import com.example.uber_backend.DTO.RequestDTO.BookingRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.BookingResponseDTO;
import com.example.uber_backend.Entity.Booking;
import com.example.uber_backend.Entity.Cab;
import com.example.uber_backend.Entity.Customer;
import com.example.uber_backend.Entity.Driver;
import com.example.uber_backend.Enums.TripStatus;

public class BookingTransformer {
    public static Booking DTOtoEntity(BookingRequestDTO bookingRequestDTO, double perKmRate)
    {
        return Booking.builder()
                .pickup(bookingRequestDTO.getPickup())
                .destination(bookingRequestDTO.getDestination())
                .tripDistanceInKm(bookingRequestDTO.getTripDistance())
                .tripStatus(TripStatus.IN_PROGRESS)
                .billAmount(bookingRequestDTO.getTripDistance() * perKmRate)
                .build();
    }

    public static BookingResponseDTO EntityToDTO(Booking booking, Customer customer, Cab cab, Driver driver)
    {
        return BookingResponseDTO.builder()
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .tripDistanceInKm(booking.getTripDistanceInKm())
                .tripStatus(booking.getTripStatus())
                .billAmount(booking.getBillAmount())
                .bookedAt(booking.getBookedAt())
                .lastUpdateAt(booking.getLastUpdateAt())
                .customer(CustomerTransformer.EntityToDTO(customer))
                .cab(CabTransformer.EntityToDTO(cab, driver))
                .build();
    }
}