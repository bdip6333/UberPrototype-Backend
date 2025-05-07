package com.example.uber_backend.Controller;

import com.example.uber_backend.DTO.RequestDTO.BookingRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.BookingResponseDTO;
import com.example.uber_backend.Services.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingServices bookingServices;

    @PostMapping("book/customer/{customerId}")
    public BookingResponseDTO createBooking(@RequestBody BookingRequestDTO bookingRequestDTO, @PathVariable("customerId") int customerId)
    {
        return bookingServices.createBooking(bookingRequestDTO, customerId);
    }
}