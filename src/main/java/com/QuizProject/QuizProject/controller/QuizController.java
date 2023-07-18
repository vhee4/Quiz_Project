package com.QuizProject.QuizProject.controller;

import com.QuizProject.QuizProject.dto.*;
import com.QuizProject.QuizProject.service.QuizService;
import com.QuizProject.QuizProject.service.ServiceImpl.ExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {
    QuizService quizService;

    @Autowired
    ExternalApi externalApi;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("uploadGerman")
    public String uploadQuiz(@RequestBody QuizDto quiz) {
        return quizService.uploadQuiz(quiz);
    }

    @PutMapping("update/{id}")
    public String updateQuiz(@PathVariable("id") Long id, @RequestBody QuizDto quizDto ) {
        return quizService.updateQuiz(id,quizDto);
    }

    @GetMapping("question/{id}")
    public QuestionResponse getAQuestionById(@PathVariable("id") Long id) {
        return quizService.getAQuestionById(id);
    }
    @GetMapping("answer/{id}")
    public AnswerRequest getAnAnswerById(@PathVariable("id") Long id) {
        return quizService.getAnAnswerById(id);
    }


    @GetMapping("allQuestions")
    public List<QuestionResponse> getAllQuestions() {
        return quizService.getAllQuestions();
    }

    @GetMapping("/{category}")
    public List<QuestionResponse> getAllQuestionsByCategory(@PathVariable(name ="category") @RequestBody String category) {
        return quizService.getAllQuestionsByCategory(category);
    }


    @GetMapping("external")
    public List<Object> getDataFromExternalApi() {
        return externalApi.callExternalApi();
    }

    @PostMapping("uploadObj")
    public String uploadQuiz(@RequestBody QuizObjDto quiz) {
        return quizService.uploadQuiz(quiz);
    }



}
