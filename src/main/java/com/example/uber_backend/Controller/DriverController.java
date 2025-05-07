package com.example.uber_backend.Controller;

import com.example.uber_backend.DTO.RequestDTO.DriverRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.DriverResponseDTO;
import com.example.uber_backend.Services.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverServices driverServices;

    //Create
    @PostMapping("/create")
    public DriverResponseDTO addDriver(@RequestBody DriverRequestDTO driverRequestDTO)
    {
        return driverServices.addDriver(driverRequestDTO);
    }

    //Update

    //Read
    @GetMapping("/read")
    public DriverResponseDTO getDriver(@PathVariable("id") int driverId)
    {
        return driverServices.getDriver(driverId);
    }

}
