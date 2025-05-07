package com.example.uber_backend.Services;

import com.example.uber_backend.DTO.RequestDTO.DriverRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.DriverResponseDTO;
import com.example.uber_backend.Entity.Driver;
import com.example.uber_backend.Exception.CustomerNotFoundException;
import com.example.uber_backend.Repository.DriverRepository;
import com.example.uber_backend.Transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverServices
{
    @Autowired
    DriverRepository driverRepository;

    public DriverResponseDTO addDriver(DriverRequestDTO driverRequestDTO) {
        Driver driver= DriverTransformer.driverRequestToDriver(driverRequestDTO);
        Driver savedDriver=driverRepository.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }

    public DriverResponseDTO getDriver(int driverId) {
        Optional<Driver> optionalDriver=driverRepository.findById(driverId);
        if(optionalDriver.isEmpty()) throw new CustomerNotFoundException("Invalid Customer Id");
        Driver savedDriver=optionalDriver.get();
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }
}
