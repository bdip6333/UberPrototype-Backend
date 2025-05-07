package com.example.uber_backend.Controller;

import com.example.uber_backend.DTO.RequestDTO.CabRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.CabResponseDTO;
import com.example.uber_backend.Services.CabServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
    CabServices cabServices;

    //Create
    @PostMapping("/create/{driver_id}")
    private CabResponseDTO createCab(@RequestBody CabRequestDTO cabRequestDTO,
                                     @PathVariable("driver_id") int driverId)
    {
        return cabServices.createCab(cabRequestDTO,driverId);
    }


}
