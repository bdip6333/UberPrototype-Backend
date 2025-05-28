package com.example.uber_backend.Transformer;

import com.example.uber_backend.DTO.RequestDTO.CabRequestDTO;
import com.example.uber_backend.DTO.ResponseDTO.CabResponseDTO;
import com.example.uber_backend.Entity.Cab;
import com.example.uber_backend.Entity.Driver;

public class CabTransformer {

        public static Cab DTOtoEntity(CabRequestDTO cabResponseDTO)
        {
            return Cab.builder()
                    .cabNumber(cabResponseDTO.getCabNumber())
                    .cabModel(cabResponseDTO.getCabModel())
                    .ratePerKm(cabResponseDTO.getRatePerKm())
                    .build();
        }
        public static CabResponseDTO EntityToDTO(Cab cab, Driver driver)
        {
            return CabResponseDTO.builder()
                    .cabNumber(cab.getCabNumber())
                    .cabModel(cab.getCabModel())
                    .ratePerKm(cab.getRatePerKm())
                    .available(true)
                    .driver(DriverTransformer.EntityToDTO(driver))
                    .build();
        }
}
