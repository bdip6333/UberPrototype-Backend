package com.example.uber_backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true,nullable=false)
    private int cabId;
    private  String cabNumber;
    private String cabModel;
    private double ratePerKm;
    private boolean available;

}
