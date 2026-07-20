package com.interviewportal.userservice.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.interviewportal.userservice.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}