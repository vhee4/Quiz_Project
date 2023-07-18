package com.QuizProject.QuizProject.service;

import com.QuizProject.QuizProject.dto.*;

import java.util.List;

public interface QuizService {
     String uploadQuiz(QuizDto quiz);
     public String uploadQuiz(QuizObjDto quizObjDto);
     public String updateQuiz(Long id, QuizDto quizDto );
     QuestionResponse getAQuestionById(Long id);
     AnswerRequest getAnAnswerById(Long id);
     List<QuestionResponse> getAllQuestions();
     List<AnswerRequest> getAllAnswers();
     List<QuestionResponse> getAllQuestionsByCategory(String category);




}
