package com.interviewportal.interviewservice.service;

import java.util.List;

import com.interviewportal.interviewservice.dto.AnswerRequest;
import com.interviewportal.interviewservice.dto.DashboardResponse;
import com.interviewportal.interviewservice.dto.EvaluationResponse;
import com.interviewportal.interviewservice.dto.InterviewRequest;
import com.interviewportal.interviewservice.dto.InterviewResponse;
import com.interviewportal.interviewservice.dto.MyInterviewResponse;
import com.interviewportal.interviewservice.dto.SessionDetailsResponse;

public interface InterviewService {

	InterviewResponse generateQuestions(Long userId, InterviewRequest request);

    EvaluationResponse evaluateAnswer(AnswerRequest request);

    List<MyInterviewResponse> getMyInterviews(Long userId);

    SessionDetailsResponse getSessionDetails(Long sessionId);

    DashboardResponse getDashboard(Long userId);
}