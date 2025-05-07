package com.example.uber_backend.Services;

import com.example.uber_backend.DTO.RequestDTO.CabRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.CabResponseDTO;
import com.example.uber_backend.Entity.Cab;
import com.example.uber_backend.Entity.Driver;
import com.example.uber_backend.Exception.CustomerNotFoundException;
import com.example.uber_backend.Exception.DriverNotFoundException;
import com.example.uber_backend.Repository.CabRepository;
import com.example.uber_backend.Repository.DriverRepository;
import com.example.uber_backend.Transformer.CabTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabServices {

    @Autowired
    CabRepository cabRepository;
    @Autowired
    DriverRepository driverRepository;

    public CabResponseDTO createCab(CabRequestDTO cabRequestDTO, int driverId)
    {
        Optional<Driver> optionalDriver=driverRepository.findById(driverId);
        if(optionalDriver.isEmpty()) throw new DriverNotFoundException("Invalid Driver Id");

        Driver driver=optionalDriver.get();


        Cab cab= CabTransformer.cabRequestToCab(cabRequestDTO);
        driver.setCab(cab);
        Driver saveDriver=driverRepository.save(driver);

//        Cab savedCab=cabRepository.save(cab);
        return CabTransformer.cabToCabResponse(saveDriver.getCab(),saveDriver);
    }
}
