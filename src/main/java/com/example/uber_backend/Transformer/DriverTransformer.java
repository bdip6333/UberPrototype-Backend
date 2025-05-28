package com.example.uber_backend.Transformer;

import com.example.uber_backend.DTO.RequestDTO.DriverRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.DriverResponseDTO;
import com.example.uber_backend.Entity.Driver;

public class DriverTransformer {

    public static Driver DTOtoEntity(DriverRequestDTO driverRequestDTO)
    {
        return Driver.builder()
            .name(driverRequestDTO.getName())
            .age(driverRequestDTO.getAge())
                .emailId((driverRequestDTO.getEmailId()))
            .build();
    }
    public static DriverResponseDTO EntityToDTO(Driver driver)
    {
        return DriverResponseDTO.builder()
            .name(driver.getName())
            .age(driver.getAge())
            .emailId(driver.getEmailId())
            .build();
    }
}
