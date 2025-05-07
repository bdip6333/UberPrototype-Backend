package com.example.uber_backend.Entity;

import com.example.uber_backend.Enums.Gender;
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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true,nullable=false)
    private int customerId;

    private String name;
    private int age;
    @Column(unique=true,nullable=false)
    private String emailId;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="customerId")
    List<Booking> booking = new ArrayList<>();
}
