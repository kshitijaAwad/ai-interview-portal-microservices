package com.interviewportal.interviewservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.interviewportal.interviewservice.entity.InterviewAnswer;

public interface InterviewAnswerRepository
        extends JpaRepository<InterviewAnswer, Long> {

    @Query("""
            SELECT AVG(a.score)
            FROM InterviewAnswer a
            WHERE a.question.session.userId = :userId
            """)
    Double getAverageScore(@Param("userId") Long userId);
    Optional<InterviewAnswer> findByQuestionId(Long questionId);

}