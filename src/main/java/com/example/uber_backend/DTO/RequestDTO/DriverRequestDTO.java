package com.example.uber_backend.DTO.RequestDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DriverRequestDTO {
    private String name;
    private int age;
    private String emailId;
}
