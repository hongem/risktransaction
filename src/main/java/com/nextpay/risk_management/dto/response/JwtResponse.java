package com.nextpay.risk_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {
	private String token;
	private String type ;//= "Bearer";
	private String email;
}
