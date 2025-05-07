package com.example.uber_backend.DTO.ResponseDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CabResponseDTO {
    private  String cabNumber;
    private String cabModel;
    private double ratePerKm;
    private boolean available;
    private DriverResponseDTO driver;
}
