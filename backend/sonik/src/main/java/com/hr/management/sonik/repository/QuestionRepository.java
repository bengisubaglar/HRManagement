package com.hr.management.sonik.repository;

import com.hr.management.sonik.entity.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long> {


    @Query(value = "SELECT * FROM QUESTION WHERE TEST_ID = ?1", nativeQuery = true)
    List<Question> findAllByTest(Long testId);

    @Modifying
    @Query(value = "INSERT INTO QUESTION ( TEST_ID, TITLE, DESCRIPTION, MARK ) VALUES ( ?1, ?2, ?3, ?4 )", nativeQuery = true)
    void addQuestion(Long testId, String title, String description, String mark);

}
