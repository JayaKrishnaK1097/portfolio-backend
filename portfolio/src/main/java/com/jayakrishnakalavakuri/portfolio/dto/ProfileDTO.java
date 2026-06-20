package com.jayakrishnakalavakuri.portfolio.dto;

// Imports
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private Long id;

    @NotBlank(message = "Full name is required.")
    private String fullName;

    private String title;
    private String description;

    @Email(message = "Email must be valid.")
    @NotBlank(message = "Email is required.")
    private String email;

    private String photoUrl;
}
