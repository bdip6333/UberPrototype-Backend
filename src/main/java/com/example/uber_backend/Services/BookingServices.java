package com.example.uber_backend.Services;

import com.example.uber_backend.DTO.RequestDTO.BookingRequestDTO;
import com.example.uber_backend.DTO.RequestDTO.CabRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.BookingResponseDTO;
import com.example.uber_backend.DTO.ResponseDTO.CabResponseDTO;
import com.example.uber_backend.Entity.Booking;
import com.example.uber_backend.Entity.Cab;
import com.example.uber_backend.Entity.Customer;
import com.example.uber_backend.Entity.Driver;
import com.example.uber_backend.Exception.CabUnavailableException;
import com.example.uber_backend.Exception.CustomerNotFoundException;
import com.example.uber_backend.Exception.DriverNotFoundException;
import com.example.uber_backend.Repository.BookingRepository;
import com.example.uber_backend.Repository.CabRepository;
import com.example.uber_backend.Repository.CustomerRepository;
import com.example.uber_backend.Repository.DriverRepository;
import com.example.uber_backend.Transformer.BookingTransformer;
import com.example.uber_backend.Transformer.CabTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO,int customerId)
    {

        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) throw new CustomerNotFoundException("Invalid Customer Id");

        Customer customer=optionalCustomer.get();
        Cab availableCab=cabRepository.getAvailableCabRandomly();
        if(availableCab==null)
        {
            throw new CabUnavailableException("Sorry! No cabs available");
        }
        Booking booking= BookingTransformer.bookingRequestToBooking(bookingRequestDTO,availableCab.getRatePerKm());
        Booking savedBooking = bookingRepository.save(booking);

        availableCab.setAvailable(false);
        customer.getBooking().add(savedBooking);

        Driver driver=driverRepository.getDriverByCabId(availableCab.getCabId());
        driver.getBooking().add(savedBooking);

        Customer savedCustomer=customerRepository.save(customer);
        Driver savedDriver=driverRepository.save(driver);

        sendEmail(savedCustomer);

        return BookingTransformer.bookingToBookingResponse(savedBooking,savedCustomer,availableCab,savedDriver);


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
}

