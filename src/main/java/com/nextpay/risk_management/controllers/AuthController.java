package com.nextpay.risk_management.controllers;

import com.nextpay.risk_management.dto.request.LoginRequest;
import com.nextpay.risk_management.dto.request.SignupRequest;
import com.nextpay.risk_management.dto.response.MessageResponse;
import com.nextpay.risk_management.security.jwt.JwtUtils;
import com.nextpay.risk_management.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


	@Autowired
	AuthService authService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return authService.registerUser(signUpRequest);

	}
}
