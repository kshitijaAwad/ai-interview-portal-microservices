package com.interviewportal.interviewservice.dto;

import com.interviewportal.interviewservice.entity.Difficulty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewRequest {

    @NotBlank
    private String domain;

    @NotNull
    private Difficulty difficulty;
}