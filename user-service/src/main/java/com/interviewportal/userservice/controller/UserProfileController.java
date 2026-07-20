package com.interviewportal.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.interviewportal.userservice.dto.UserProfileRequest;
import com.interviewportal.userservice.dto.UserProfileResponse;
import com.interviewportal.userservice.service.UserProfileService;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
    @PostMapping
    public ResponseEntity<UserProfileResponse> createProfile(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody UserProfileRequest request) {

        return new ResponseEntity<>(
                userProfileService.createProfile(userId, request),
                HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(
                userProfileService.getProfileByUserId(userId));
    }
    
    @PutMapping
    public ResponseEntity<UserProfileResponse> updateProfile(
    		@RequestHeader("X-User-Id") Long userId,
            @RequestBody UserProfileRequest request) {

        return ResponseEntity.ok(
                userProfileService.updateProfile(userId, request));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProfile(@RequestHeader("X-User-Id") Long userId) {

        userProfileService.deleteProfile(userId);
        return ResponseEntity.ok("Profile deleted successfully");
    }
}