package com.QuizProject.QuizProject.service;

import com.QuizProject.QuizProject.dto.AnswerRequest;
import com.QuizProject.QuizProject.dto.UserDto;
import com.QuizProject.QuizProject.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse CreateUser(UserDto user);
    UserResponse updateUser(UserDto user, UserDto newUser);
    UserResponse scoreUser(UserDto user, AnswerRequest answerRequest,Long quizId);
    List<UserDto> fetchAllUsers();

    String DeleteUser(Long id);

}
