package com.QuizProject.QuizProject.repository;
import com.QuizProject.QuizProject.dto.QuizDto;
import com.QuizProject.QuizProject.dto.QuizObjDto;
import com.QuizProject.QuizProject.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Long> {
    boolean existsById(Long id);
//    boolean existsByQuestion(QuizDto quiz);
//    boolean existsByQuestion(QuizObjDto quiz);
    List<Quiz> findAllByCategory(String category);

    boolean existsByQuestion(String question);
}
