package com.example.uber_backend.Services;

import com.example.uber_backend.DTO.RequestDTO.DriverRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.DriverResponseDTO;
import com.example.uber_backend.Entity.Driver;
import com.example.uber_backend.Exception.CustomerNotFoundException;
import com.example.uber_backend.Exception.DriverNotFoundException;
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

    public DriverResponseDTO createDriver(DriverRequestDTO driverRequestDTO) {
        Driver driver= DriverTransformer.DTOtoEntity(driverRequestDTO);
        Driver savedDriver=driverRepository.save(driver);
        return DriverTransformer.EntityToDTO(savedDriver);
    }

    public DriverResponseDTO readDriver(int driverId) {
        Optional<Driver> optionalDriver=driverRepository.findById(driverId);
        if(optionalDriver.isEmpty()) throw new DriverNotFoundException("Invalid Driver Id");
        Driver savedDriver=optionalDriver.get();
        return DriverTransformer.EntityToDTO(savedDriver);
    }

    public  DriverResponseDTO updateDriver(int driverId, DriverRequestDTO driverRequestDTO)
    {
        Optional<Driver> optionalDriver=driverRepository.findById(driverId);
        if(optionalDriver.isEmpty()) throw new DriverNotFoundException("Invalid Driver Id");

        //DTO to Entity
        Driver updateDriver=optionalDriver.get();
        updateDriver.setName(driverRequestDTO.getName());
        updateDriver.setAge(driverRequestDTO.getAge());
        updateDriver.setEmailId(driverRequestDTO.getEmailId());

        //Update customerId
        updateDriver.setDriverId(driverId);

//        updateDriver.setBooking(optionalDriver.get().getBooking());

        Driver saveDriver=driverRepository.save(updateDriver);

        //Entity to DTO
        DriverResponseDTO driverResponseDTO=new DriverResponseDTO();
        driverResponseDTO.setName(saveDriver.getName());
        driverResponseDTO.setAge(saveDriver.getAge());
        driverResponseDTO.setEmailId(saveDriver.getEmailId());

        return driverResponseDTO;

    }

    public DriverResponseDTO deleteDriver(int driverId)
    {
        Optional<Driver> optionalDriver=driverRepository.findById(driverId);
        if(optionalDriver.isEmpty()) throw new DriverNotFoundException("Invalid Driver Id");

        Driver fetchDriver=optionalDriver.get();

        //Entity to DTO
        DriverResponseDTO driverResponseDTO=new DriverResponseDTO();
        driverResponseDTO.setName(fetchDriver.getName());
        driverResponseDTO.setAge(fetchDriver.getAge());
        driverResponseDTO.setEmailId(fetchDriver.getEmailId());

        driverRepository.deleteById(driverId);

        return driverResponseDTO;

    }
}
