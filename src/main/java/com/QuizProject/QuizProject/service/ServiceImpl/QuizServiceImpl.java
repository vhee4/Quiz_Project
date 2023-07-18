package com.QuizProject.QuizProject.service.ServiceImpl;

import com.QuizProject.QuizProject.dto.AnswerRequest;
import com.QuizProject.QuizProject.dto.QuestionResponse;
import com.QuizProject.QuizProject.dto.QuizDto;
import com.QuizProject.QuizProject.dto.QuizObjDto;
import com.QuizProject.QuizProject.models.Quiz;
import com.QuizProject.QuizProject.repository.QuizRepo;
import com.QuizProject.QuizProject.service.QuizService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    QuizRepo quizRepo;
    private final  RestTemplate restTemplate;

    public QuizServiceImpl(QuizRepo quizRepo, RestTemplate restTemplate) {
        this.quizRepo = quizRepo;
        this.restTemplate = restTemplate;
    }

    //this is for german questions
    @Override
    public String uploadQuiz(QuizDto quiz) {
        if(quizRepo.existsByQuestion(quiz.getQuestion())){
            return "Quiz already exists";
        }
        Quiz quiz1 = Quiz.builder()
                .question(quiz.getQuestion())
                .correctAnswer(quiz.getCorrectAnswer())
                .category(quiz.getCategory())
                .build();

        quizRepo.save(quiz1);
        return "Quiz successfully uploaded";
    }

   //this is for obj questions
    @Override
    public String uploadQuiz(QuizObjDto quizObjDto) {
        if(quizRepo.existsByQuestion(quizObjDto.getQuestion())){
            return "Quiz already exists";
        }
        Quiz quiz1 = Quiz.builder()
                .category(quizObjDto.getCategory())
                .correctAnswer(quizObjDto.getCorrectAnswer())
                .option1(quizObjDto.getOption1())
                .option2(quizObjDto.getOption2())
                .option3(quizObjDto.getOption3())
                .option4(quizObjDto.getOption4())
                .question(quizObjDto.getQuestion())
                .build();
        quizRepo.save(quiz1);
        return "Quiz successfully uploaded";
    }

    @Override
//    @Cacheable(cacheNames = "quiz",key = "#question.id")
    public String updateQuiz(Long id, QuizDto quizDto ) {
        boolean isExists = quizRepo.existsById(id);
        if(!isExists){
            return "Quiz does not exist";
        }
        Quiz foundQuiz =  quizRepo.findById(id).get();
        foundQuiz.setQuestion(quizDto.getQuestion());
        foundQuiz.setCorrectAnswer(quizDto.getCorrectAnswer());
        quizRepo.save(foundQuiz);
        return "User successfully updated";
    }

    @Override
//    @Cacheable(cacheNames = "question",key = "#id")
    public QuestionResponse getAQuestionById(Long id) {
        boolean isExists = quizRepo.existsById(id);
        if(!isExists){
            return QuestionResponse.builder()
                    .question(null)
                    .build();
        }
        Quiz quiz = quizRepo.findById(id).get();
        return QuestionResponse.builder()
                .id(quiz.getQuizId())
                .question(quiz.getQuestion())
                .build();
    }

    @Override
//    @Cacheable(cacheNames = "answer")
    public AnswerRequest getAnAnswerById(Long id) {
        boolean isExists = quizRepo.existsById(id);
        if(!isExists){
            return AnswerRequest.builder()
                    .answer(null)
                    .build();
        }
        Quiz quiz = quizRepo.findById(id).get();
        return AnswerRequest.builder()
                .answer(quiz.getCorrectAnswer())
                .build();
    }

    @Override
//    @Cacheable(cacheNames = "question")
    public List<QuestionResponse> getAllQuestions() {
        List<Quiz> quiz = quizRepo.findAll();
        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (Quiz quiz1: quiz){
            questionResponses.add(QuestionResponse.builder()
                            .id(quiz1.getQuizId())
                            .question(quiz1.getQuestion())
                    .build());
        }
        Collections.shuffle(questionResponses);
        return questionResponses.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public List<QuestionResponse> getAllQuestionsByCategory(String category) {
        List<Quiz> quiz = quizRepo.findAllByCategory(category);
        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (Quiz quiz1: quiz){
            questionResponses.add(QuestionResponse.builder()
                            .question(quiz1.getQuestion())
                    .build());
        }
        Collections.shuffle(questionResponses);
        return questionResponses;
    }


    @Override
//    @Cacheable(cacheNames = "answer")
    public List<AnswerRequest> getAllAnswers() {
        List<Quiz> quiz = quizRepo.findAll();
        List<AnswerRequest> answerRequests = new ArrayList<>();
        for (Quiz quiz1: quiz){
            answerRequests.add(AnswerRequest.builder()
                    .answer(quiz1.getCorrectAnswer())
                    .build());
        }
        return answerRequests;
    }


}
