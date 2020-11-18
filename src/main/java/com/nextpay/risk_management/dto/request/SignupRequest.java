package com.nextpay.risk_management.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(max = 30)
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;



}
