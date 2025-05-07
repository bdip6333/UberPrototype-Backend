package com.example.uber_backend.Controller;

import com.example.uber_backend.DTO.RequestDTO.CustomerRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.CustomerResponseDTO;
import com.example.uber_backend.Entity.Customer;
import com.example.uber_backend.Services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServices customerServices;

    //Create
    @PostMapping("/create")
    public CustomerResponseDTO postCustomer(@RequestBody CustomerRequestDTO customerRequestDTO)
    {

        return customerServices.postCustomer(customerRequestDTO);
    }

    //Update
    @PutMapping("/update/{id}")
    public CustomerResponseDTO putCustomer(@PathVariable("id") int customerId, @RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerServices.putCustomer(customerId, customerRequestDTO);
    }

    //Read
    @GetMapping("read/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable("id") int customerId)
    {
        return customerServices.getCustomer(customerId);
    }

    //Delete
    @DeleteMapping("delete/{id}")
    public CustomerResponseDTO deleteCustomer(@PathVariable("id") int customerId)
    {
        return customerServices.deleteCustomer(customerId);
    }


}
