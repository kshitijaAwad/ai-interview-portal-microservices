package com.interviewportal.userservice.service;

import com.interviewportal.userservice.dto.UserProfileRequest;
import com.interviewportal.userservice.dto.UserProfileResponse;

public interface UserProfileService {

    UserProfileResponse createProfile(Long userId, UserProfileRequest request);

    UserProfileResponse getProfileByUserId(Long userId);

    UserProfileResponse updateProfile(Long userId, UserProfileRequest request);

    void deleteProfile(Long userId);
}