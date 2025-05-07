package com.example.uber_backend.DTO.RequestDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CabRequestDTO {
    private  String cabNumber;
    private String cabModel;
    private double ratePerKm;
}
