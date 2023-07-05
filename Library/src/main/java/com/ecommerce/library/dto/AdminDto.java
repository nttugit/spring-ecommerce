package com.ecommerce.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    @Size(min = 3, max =10, message = "First name must be 3-10 character length")
    private String firstName;

    @Size(min = 3, max =10, message = "Last name must be 3-10 character length")
    private String lastName;

    private String username;

    @Size(min = 6, max =20, message = "Password must be 6-20 character length")
    private String password;

    private String repeatPassword;
}
