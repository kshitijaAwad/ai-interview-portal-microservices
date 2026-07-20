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

    @PostMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> createProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileRequest request) {

        return new ResponseEntity<>(
                userProfileService.createProfile(userId, request),
                HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> getProfile(@PathVariable Long userId) {

        return ResponseEntity.ok(userProfileService.getProfileByUserId(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileRequest request) {

        return ResponseEntity.ok(
                userProfileService.updateProfile(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long userId) {

        userProfileService.deleteProfile(userId);
        return ResponseEntity.ok("Profile deleted successfully");
    }
}