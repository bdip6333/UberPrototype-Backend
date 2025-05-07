package com.example.uber_backend.DTO.RequestDTO;

import com.example.uber_backend.Enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequestDTO {
    private String name;
    private int age;
    private String emailId;
    private Gender gender;
}
