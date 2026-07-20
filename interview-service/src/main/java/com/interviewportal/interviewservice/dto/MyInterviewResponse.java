package com.interviewportal.interviewservice.dto;

import java.time.LocalDateTime;

import com.interviewportal.interviewservice.entity.Difficulty;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyInterviewResponse {

    private Long sessionId;

    private String domain;

    private Difficulty difficulty;

    private LocalDateTime createdAt;
}