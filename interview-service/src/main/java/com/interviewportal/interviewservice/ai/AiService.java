package com.interviewportal.interviewservice.ai;

import java.util.List;

import com.interviewportal.interviewservice.entity.Difficulty;

public interface AiService {

    List<String> generateQuestions(String domain, Difficulty difficulty);

    String evaluateAnswer(String prompt);
}