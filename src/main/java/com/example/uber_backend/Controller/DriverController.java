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
    public DriverResponseDTO createDriver(@RequestBody DriverRequestDTO driverRequestDTO)
    {
        return driverServices.createDriver(driverRequestDTO);
    }

    //Read
    @GetMapping("/read/{id}")
    public DriverResponseDTO readDriver(@PathVariable("id") int driverId)
    {
        return driverServices.readDriver(driverId);
    }

    //Update
    @PutMapping("/update/{id}")
    public DriverResponseDTO updateDriver(@PathVariable("id") int driverId,@RequestBody DriverRequestDTO driverRequestDTO)
    {
        return driverServices.updateDriver(driverId,driverRequestDTO);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public DriverResponseDTO deleteDriver(@PathVariable("id") int driverId)
    {
        return driverServices.deleteDriver(driverId);
    }

}
