package com.example.uber_backend.DTO.ResponseDTO;

import com.example.uber_backend.Enums.TripStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponseDTO {
    private String pickup;
    private String destination;
    private double tripDistanceInKm;
    private TripStatus tripStatus;
    private  double billAmount;
    private Date bookedAt;
    private Date lastUpdateAt;
    CustomerResponseDTO customer;
    CabResponseDTO cab;
}
