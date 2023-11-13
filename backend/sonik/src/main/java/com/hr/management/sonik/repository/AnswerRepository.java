package com.hr.management.sonik.repository;

import com.hr.management.sonik.entity.Answer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Modifying
    @Query(value = "INSERT INTO ANSWER ( QUESTION_ID, OPTIONS, IS_CORRECT) VALUES ( ?1, ?2, ?3 )", nativeQuery = true)
    void addAnswer(Long questionId, String options, boolean isCorrect);

}
