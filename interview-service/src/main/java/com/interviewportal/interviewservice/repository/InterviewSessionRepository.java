package com.interviewportal.interviewservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.interviewportal.interviewservice.entity.InterviewSession;

public interface InterviewSessionRepository
        extends JpaRepository<InterviewSession, Long> {

    List<InterviewSession> findByUserId(Long userId);
    
    List<InterviewSession> findByUserIdOrderByCreatedAtDesc(Long userId);
    
//    List<InterviewSession> findAllByOrderByCreatedAtDesc();

    Long countByUserId(Long userId);
}