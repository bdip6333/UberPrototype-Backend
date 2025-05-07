package com.example.uber_backend.DTO.ResponseDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponseDTO {
    private String name;
    private int age;
    private String emailId;
}
