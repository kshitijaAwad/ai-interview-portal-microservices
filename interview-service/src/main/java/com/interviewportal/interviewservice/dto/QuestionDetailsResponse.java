package com.interviewportal.interviewservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDetailsResponse {

    private Long questionId;

    private String question;

    private String answer;

    private Integer score;

    private String feedback;
}