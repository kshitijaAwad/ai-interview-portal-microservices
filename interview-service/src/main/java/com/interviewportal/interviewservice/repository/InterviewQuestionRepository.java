package com.interviewportal.interviewservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.interviewportal.interviewservice.entity.InterviewQuestion;

public interface InterviewQuestionRepository
        extends JpaRepository<InterviewQuestion, Long> {

    List<InterviewQuestion> findBySessionId(Long sessionId);

    Long countBySessionUserId(Long userId);
}