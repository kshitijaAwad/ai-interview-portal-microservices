package com.interviewportal.interviewservice.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.interviewportal.interviewservice.ai.AiService;
import com.interviewportal.interviewservice.dto.AnswerRequest;
import com.interviewportal.interviewservice.dto.DashboardResponse;
import com.interviewportal.interviewservice.dto.EvaluationResponse;
import com.interviewportal.interviewservice.dto.InterviewRequest;
import com.interviewportal.interviewservice.dto.InterviewResponse;
import com.interviewportal.interviewservice.dto.MyInterviewResponse;
import com.interviewportal.interviewservice.dto.QuestionDetailsResponse;
import com.interviewportal.interviewservice.dto.QuestionResponse;
import com.interviewportal.interviewservice.dto.SessionDetailsResponse;
import com.interviewportal.interviewservice.entity.InterviewAnswer;
import com.interviewportal.interviewservice.entity.InterviewQuestion;
import com.interviewportal.interviewservice.entity.InterviewSession;
import com.interviewportal.interviewservice.repository.InterviewAnswerRepository;
import com.interviewportal.interviewservice.repository.InterviewQuestionRepository;
import com.interviewportal.interviewservice.repository.InterviewSessionRepository;
import com.interviewportal.interviewservice.service.InterviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

	private final AiService aiService;
    private final InterviewSessionRepository sessionRepository;
    private final InterviewQuestionRepository questionRepository;
    private final InterviewAnswerRepository answerRepository;

    @Override
    public InterviewResponse generateQuestions(Long userId,
            InterviewRequest request) {

    	InterviewSession session = InterviewSession.builder()
    			.userId(userId)
    	        .domain(request.getDomain())
    	        .difficulty(request.getDifficulty())
    	        .createdAt(LocalDateTime.now())
    	        .build();

        session = sessionRepository.save(session);

        List<String> aiQuestions =
                aiService.generateQuestions(
                        request.getDomain(),
                        request.getDifficulty());

        List<QuestionResponse> responses = new ArrayList<>();

        for (String questionText : aiQuestions) {

            InterviewQuestion question =
                    InterviewQuestion.builder()
                            .questionText(questionText)
                            .createdAt(LocalDateTime.now())
                            .session(session)
                            .build();

            question = questionRepository.save(question);

            responses.add(
                    QuestionResponse.builder()
                            .questionId(question.getId())
                            .question(question.getQuestionText())
                            .build());
        }

        return InterviewResponse.builder()
                .sessionId(session.getId())
                .questions(responses)
                .build();
    }

    @Override
    public EvaluationResponse evaluateAnswer(AnswerRequest request) {

        InterviewQuestion question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        String prompt = """
                You are an expert technical interviewer.

                Question:
                %s

                Candidate Answer:
                %s

                Evaluate the answer.

                Give output in exactly this format:

                Score: X/10
                Feedback: Your feedback here.
                """
                .formatted(
                        question.getQuestionText(),
                        request.getAnswer());

        String aiResponse = aiService.evaluateAnswer(prompt);

        int score = 0;
        String feedback = aiResponse;

        Pattern pattern = Pattern.compile("Score:\\s*(\\d+)");
        Matcher matcher = pattern.matcher(aiResponse);

        if (matcher.find()) {
            score = Integer.parseInt(matcher.group(1));
        }

        if (aiResponse.contains("Feedback:")) {
            feedback = aiResponse.substring(
                    aiResponse.indexOf("Feedback:") + 9).trim();
        }

        InterviewAnswer answer = InterviewAnswer.builder()
                .answerText(request.getAnswer())
                .score(score)
                .feedback(feedback)
                .question(question)
                .build();

        answerRepository.save(answer);

        return EvaluationResponse.builder()
                .score(score)
                .feedback(feedback)
                .build();
    }

    @Override
    public List<MyInterviewResponse> getMyInterviews(Long userId) {

        List<InterviewSession> sessions =
        		sessionRepository.findByUserIdOrderByCreatedAtDesc(userId);

        return sessions.stream()
                .map(session -> MyInterviewResponse.builder()
                        .sessionId(session.getId())
                        .domain(session.getDomain())
                        .difficulty(session.getDifficulty())
                        .createdAt(session.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    public SessionDetailsResponse getSessionDetails(Long sessionId) {

        InterviewSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Interview session not found"));

        List<InterviewQuestion> questions =
                questionRepository.findBySessionId(sessionId);

        List<QuestionDetailsResponse> questionResponses = new ArrayList<>();

        for (InterviewQuestion question : questions) {

            InterviewAnswer answer = answerRepository
                    .findByQuestionId(question.getId())
                    .orElse(null);

            QuestionDetailsResponse response =
                    QuestionDetailsResponse.builder()
                            .questionId(question.getId())
                            .question(question.getQuestionText())
                            .answer(answer != null ? answer.getAnswerText() : null)
                            .score(answer != null ? answer.getScore() : null)
                            .feedback(answer != null ? answer.getFeedback() : null)
                            .build();

            questionResponses.add(response);
        }

        return SessionDetailsResponse.builder()
                .sessionId(session.getId())
                .domain(session.getDomain())
                .difficulty(session.getDifficulty())
                .questions(questionResponses)
                .build();
    }
    
    @Override
    public DashboardResponse getDashboard(Long userId) {

     
        Long totalInterviews = sessionRepository.countByUserId(userId);

        Long totalQuestions = questionRepository.countBySessionUserId(userId);

        Double averageScore = answerRepository.getAverageScore(userId);

        if (averageScore == null) {
            averageScore = 0.0;
        }

        return DashboardResponse.builder()
                .totalInterviews(totalInterviews)
                .totalQuestions(totalQuestions)
                .averageScore(averageScore)
                .build();
    }
}
