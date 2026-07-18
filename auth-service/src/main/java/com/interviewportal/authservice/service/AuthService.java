package com.interviewportal.authservice.service;

import com.interviewportal.authservice.dto.LoginRequest;
import com.interviewportal.authservice.dto.LoginResponse;
import com.interviewportal.authservice.dto.RegisterRequest;

import jakarta.validation.Valid;

public interface AuthService {

	String register(RegisterRequest request);

	LoginResponse login(@Valid LoginRequest request);
}
