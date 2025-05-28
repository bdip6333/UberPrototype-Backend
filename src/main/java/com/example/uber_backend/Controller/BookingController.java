package com.example.uber_backend.Controller;

import com.example.uber_backend.DTO.RequestDTO.BookingRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.BookingResponseDTO;
import com.example.uber_backend.DTO.ResponseDTO.CustomerResponseDTO;
import com.example.uber_backend.Entity.Customer;
import com.example.uber_backend.Services.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingServices bookingServices;

    @PostMapping("create/{customerId}")
    public BookingResponseDTO createBooking(@RequestBody BookingRequestDTO bookingRequestDTO, @PathVariable("customerId") int customerId)
    {
        return bookingServices.createBooking(bookingRequestDTO, customerId);
    }

    @GetMapping("{id}")
    public BookingResponseDTO readBooking(@PathVariable("id") int bookingID)
    {
        return bookingServices.readBooking(bookingID);
    }

}