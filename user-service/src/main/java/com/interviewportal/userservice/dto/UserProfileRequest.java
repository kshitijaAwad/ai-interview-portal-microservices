package com.interviewportal.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileRequest {

    private String fullName;
    private String email;
    private String phone;
    private String college;
    private String degree;
    private String skills;
    private String resumeUrl;
}