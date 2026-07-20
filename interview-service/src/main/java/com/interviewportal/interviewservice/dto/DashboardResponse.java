package com.interviewportal.interviewservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private Long totalInterviews;

    private Long totalQuestions;

    private Double averageScore;
}