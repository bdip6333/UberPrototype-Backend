package com.example.uber_backend.Services;

import com.example.uber_backend.DTO.RequestDTO.CustomerRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.CustomerResponseDTO;
import com.example.uber_backend.Entity.Customer;
import com.example.uber_backend.Exception.CustomerNotFoundException;
import com.example.uber_backend.Repository.CustomerRepository;
import com.example.uber_backend.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer= CustomerTransformer.DTOtoEntity(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerTransformer.EntityToDTO(savedCustomer);
    }

    public CustomerResponseDTO updateCustomer(int customerId, CustomerRequestDTO customerRequestDTO)
    {
        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) throw new CustomerNotFoundException("Invalid Customer Id");

        // Create updated customer from request
        Customer updatedCustomer = CustomerTransformer.DTOtoEntity(customerRequestDTO);

        // Maintain the same ID
        updatedCustomer.setCustomerId(customerId);

        // Keep existing bookings (if needed)
        updatedCustomer.setBooking(optionalCustomer.get().getBooking());

        // Save and return
        Customer savedCustomer = customerRepository.save(updatedCustomer);
        return CustomerTransformer.EntityToDTO(savedCustomer);
    }


    public CustomerResponseDTO readCustomer(int customerId) {
        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) throw new CustomerNotFoundException("Invalid Customer Id");
        Customer savedCustomer=optionalCustomer.get();
        return CustomerTransformer.EntityToDTO(savedCustomer);
    }

    public CustomerResponseDTO deleteCustomer(int customerId)
    {
        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) throw new CustomerNotFoundException("Invalid Customer Id");

        Customer customer = optionalCustomer.get();

        // Save customer data before deletion to return in response
        CustomerResponseDTO responseDTO = CustomerTransformer.EntityToDTO(customer);

        // Delete the customer
        customerRepository.delete(customer);

        return responseDTO;
    }

}
