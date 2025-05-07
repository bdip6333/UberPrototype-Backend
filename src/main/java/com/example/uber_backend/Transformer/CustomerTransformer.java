package com.example.uber_backend.Transformer;

import com.example.uber_backend.DTO.RequestDTO.CustomerRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.CustomerResponseDTO;
import com.example.uber_backend.Entity.Customer;

public class CustomerTransformer
{
    public static Customer customerRequestToCustomer(CustomerRequestDTO customerRequestDTO)
    {
        return Customer.builder()
                  .name(customerRequestDTO.getName())
                  .age(customerRequestDTO.getAge())
                  .emailId(customerRequestDTO.getEmailId())
                  .gender(customerRequestDTO.getGender())
                  .build();
    }
    public static CustomerResponseDTO customerToCustomerResponse(Customer customer)
    {
        return CustomerResponseDTO.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .emailId(customer.getEmailId())
                .build();
    }
}
