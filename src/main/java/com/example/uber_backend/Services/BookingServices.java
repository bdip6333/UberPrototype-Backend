package com.example.uber_backend.Services;

import com.example.uber_backend.DTO.RequestDTO.BookingRequestDTO;
import com.example.uber_backend.DTO.RequestDTO.CabRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.BookingResponseDTO;
import com.example.uber_backend.DTO.ResponseDTO.CabResponseDTO;
import com.example.uber_backend.DTO.ResponseDTO.CustomerResponseDTO;
import com.example.uber_backend.Entity.Booking;
import com.example.uber_backend.Entity.Cab;
import com.example.uber_backend.Entity.Customer;
import com.example.uber_backend.Entity.Driver;
import com.example.uber_backend.Enums.TripStatus;
import com.example.uber_backend.Exception.BookingNotFoundException;
import com.example.uber_backend.Exception.CabUnavailableException;
import com.example.uber_backend.Exception.CustomerNotFoundException;
import com.example.uber_backend.Exception.DriverNotFoundException;
import com.example.uber_backend.Repository.BookingRepository;
import com.example.uber_backend.Repository.CabRepository;
import com.example.uber_backend.Repository.CustomerRepository;
import com.example.uber_backend.Repository.DriverRepository;
import com.example.uber_backend.Transformer.BookingTransformer;
import com.example.uber_backend.Transformer.CabTransformer;
import com.example.uber_backend.Transformer.CustomerTransformer;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServices {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CabRepository cabRepository;
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    JavaMailSender javaMailSender;


    //Create
    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO, int customerId)
    {
        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) throw new CustomerNotFoundException("Invalid Customer");

        Cab availableCab=cabRepository.getAvailableCabRandomly();
        if(availableCab==null) throw new CabUnavailableException("Cab Not Available");

        //Boking DTO to Entity
        Booking booking=new Booking();
        booking.setPickup(bookingRequestDTO.getPickup());
        booking.setDestination(bookingRequestDTO.getDestination());
        booking.setTripDistanceInKm(bookingRequestDTO.getTripDistance());
        booking.setTripStatus(TripStatus.IN_PROGRESS);
        booking.setBillAmount(bookingRequestDTO.getTripDistance() * availableCab.getRatePerKm());

        Booking savedBooking=bookingRepository.save(booking);

        availableCab.setAvailable(false);

        optionalCustomer.get().getBooking().add(savedBooking);


        Driver driver=driverRepository.getDriverByCabId(availableCab.getCabId());
        driver.getBooking().add(savedBooking);

        Customer savedCustomer=customerRepository.save(optionalCustomer.get());
        Driver savedDriver=driverRepository.save(driver);

        //Entity to DTO
        BookingResponseDTO bookingResponseDTO=new BookingResponseDTO();
        bookingResponseDTO.setPickup(savedBooking.getPickup());
        bookingResponseDTO.setDestination(savedBooking.getDestination());
        bookingResponseDTO.setTripDistanceInKm(savedBooking.getTripDistanceInKm());
        bookingResponseDTO.setTripStatus(savedBooking.getTripStatus());
        bookingResponseDTO.setBillAmount(savedBooking.getBillAmount());
        bookingResponseDTO.setBookedAt(savedBooking.getBookedAt());
        bookingResponseDTO.setLastUpdateAt(savedBooking.getLastUpdateAt());
        bookingResponseDTO.setCustomer(CustomerTransformer.EntityToDTO(savedCustomer));
        bookingResponseDTO.setCab(CabTransformer.EntityToDTO(availableCab,savedDriver));

        sendEmail(savedCustomer);

        return bookingResponseDTO;
    }


    private void sendEmail(Customer customer)
    {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("www.pritamgoswami0817@gmail.com");
        simpleMailMessage.setTo(customer.getEmailId());
        simpleMailMessage.setSubject("Cab Booked");
        simpleMailMessage.setText("Congrats! "+customer.getName()+" Your cab has been booked");

        javaMailSender.send(simpleMailMessage);
    }

    //Read
    public BookingResponseDTO readBooking(int bookingID)
    {
        Optional<Booking> optionalBooking=bookingRepository.findById(bookingID);
        if(optionalBooking.isEmpty()) throw new BookingNotFoundException("Invalid Booking Id");

        // Find customer who has this booking (same pattern as createBooking)
        Customer savedCustomer = customerRepository.findAll().stream()
                .filter(customer -> customer.getBooking().contains(optionalBooking.get()))
                .findFirst()
                .orElse(null);

        // Find driver who has this booking (same pattern as createBooking)
        Driver savedDriver = driverRepository.findAll().stream()
                .filter(driver -> driver.getBooking().contains(optionalBooking.get()))
                .findFirst()
                .orElse(null);

        // Find cab associated with this driver (reverse of getDriverByCabId)
        Cab availableCab = null;
        if(savedDriver != null) {
            availableCab = cabRepository.findAll().stream()
                    .filter(cab -> driverRepository.getDriverByCabId(cab.getCabId()).equals(savedDriver))
                    .findFirst()
                    .orElse(null);
        }

        BookingResponseDTO bookingResponseDTO=new BookingResponseDTO();
        bookingResponseDTO.setPickup(optionalBooking.get().getPickup());
        bookingResponseDTO.setDestination(optionalBooking.get().getDestination());
        bookingResponseDTO.setTripDistanceInKm(optionalBooking.get().getTripDistanceInKm());
        bookingResponseDTO.setTripStatus(optionalBooking.get().getTripStatus());
        bookingResponseDTO.setBillAmount(optionalBooking.get().getBillAmount());
        bookingResponseDTO.setBookedAt(optionalBooking.get().getBookedAt());
        bookingResponseDTO.setLastUpdateAt(optionalBooking.get().getLastUpdateAt());
        bookingResponseDTO.setCustomer(CustomerTransformer.EntityToDTO(savedCustomer));
        bookingResponseDTO.setCab(CabTransformer.EntityToDTO(availableCab,savedDriver));

        return bookingResponseDTO;

    }

}

