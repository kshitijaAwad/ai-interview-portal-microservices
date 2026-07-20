package com.interviewportal.interviewservice.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewResponse {

    private Long sessionId;

    private List<QuestionResponse> questions;
}