package com.ecommune.gn.api.ecommune.gn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactUsRequestDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Email
    @NotBlank
    @Size(max = 150)
    private String email;

    @Size(max = 20)
    private String phone;

    @NotBlank
    @Size(max = 255)
    private String subject;

    @NotBlank
    @Size(max = 5000)
    private String message;
}
