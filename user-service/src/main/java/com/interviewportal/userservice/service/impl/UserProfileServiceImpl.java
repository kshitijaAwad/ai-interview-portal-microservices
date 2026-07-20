package com.interviewportal.userservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.interviewportal.userservice.dto.UserProfileRequest;
import com.interviewportal.userservice.dto.UserProfileResponse;
import com.interviewportal.userservice.entity.UserProfile;
import com.interviewportal.userservice.repository.UserProfileRepository;
import com.interviewportal.userservice.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfileResponse createProfile(Long userId, UserProfileRequest request) {

        UserProfile profile = new UserProfile();
        BeanUtils.copyProperties(request, profile);
        profile.setUserId(userId);

        profile = repository.save(profile);

        UserProfileResponse response = new UserProfileResponse();
        BeanUtils.copyProperties(profile, response);

        return response;
    }

    @Override
    public UserProfileResponse getProfileByUserId(Long userId) {

        UserProfile profile = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        UserProfileResponse response = new UserProfileResponse();
        BeanUtils.copyProperties(profile, response);

        return response;
    }

    @Override
    public UserProfileResponse updateProfile(Long userId, UserProfileRequest request) {

        UserProfile profile = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        BeanUtils.copyProperties(request, profile);
        profile.setUserId(userId);

        profile = repository.save(profile);

        UserProfileResponse response = new UserProfileResponse();
        BeanUtils.copyProperties(profile, response);

        return response;
    }

    @Override
    public void deleteProfile(Long userId) {

        repository.deleteById(userId);
    }
}