package com.interviewportal.interviewservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.interviewportal.interviewservice.dto.AnswerRequest;
import com.interviewportal.interviewservice.dto.DashboardResponse;
import com.interviewportal.interviewservice.dto.EvaluationResponse;
import com.interviewportal.interviewservice.dto.InterviewRequest;
import com.interviewportal.interviewservice.dto.InterviewResponse;
import com.interviewportal.interviewservice.dto.MyInterviewResponse;
import com.interviewportal.interviewservice.dto.SessionDetailsResponse;
import com.interviewportal.interviewservice.service.InterviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping("/generate")
    public ResponseEntity<InterviewResponse> generateQuestions(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody InterviewRequest request) {

        return new ResponseEntity<>(
                interviewService.generateQuestions(userId, request),
                HttpStatus.CREATED);
    }

    @PostMapping("/evaluate")
    public ResponseEntity<EvaluationResponse> evaluateAnswer(
            @Valid @RequestBody AnswerRequest request) {

        return ResponseEntity.ok(
                interviewService.evaluateAnswer(request));
    }

    @GetMapping
    public ResponseEntity<List<MyInterviewResponse>> getMyInterviews(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(
                interviewService.getMyInterviews(userId));
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<SessionDetailsResponse> getSessionDetails(
            @PathVariable Long sessionId) {

        return ResponseEntity.ok(
                interviewService.getSessionDetails(sessionId));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getDashboard(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(
                interviewService.getDashboard(userId));
    }
}