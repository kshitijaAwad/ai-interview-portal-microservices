package com.interviewportal.interviewservice.dto;

import java.util.List;

import com.interviewportal.interviewservice.entity.Difficulty;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionDetailsResponse {

    private Long sessionId;

    private String domain;

    private Difficulty difficulty;

    private List<QuestionDetailsResponse> questions;
}