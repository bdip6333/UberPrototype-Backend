package com.example.uber_backend.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true,nullable=false)
    private int driverId;
    private String name;
    private int age;
    @Column(unique=true,nullable=false)
    private String emailId;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="driverId")
    List<Booking> booking=new ArrayList<>();

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="cabId")
    Cab cab=new Cab();
}
