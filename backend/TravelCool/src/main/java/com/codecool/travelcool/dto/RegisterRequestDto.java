package com.codecool.travelcool.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

