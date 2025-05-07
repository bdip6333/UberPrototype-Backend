package com.example.uber_backend.DTO.RequestDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingRequestDTO {
    private String pickup;
    private String destination;
    private double tripDistance;
}
