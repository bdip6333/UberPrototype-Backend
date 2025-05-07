package com.example.uber_backend.Entity;

import com.example.uber_backend.Enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true,nullable=false)
    private int bookingId;
    private String pickup;
    private String destination;
    private double tripDistanceInKm;
//    @Enumerated(value = EnumType.STRING)
    private TripStatus tripStatus;

    private  double billAmount;

    @CreationTimestamp
    Date bookedAt;

    @UpdateTimestamp
    Date lastUpdateAt;
}
