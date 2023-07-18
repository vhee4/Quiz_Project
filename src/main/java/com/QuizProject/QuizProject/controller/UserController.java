package com.QuizProject.QuizProject.controller;

import com.QuizProject.QuizProject.dto.AnswerRequest;
import com.QuizProject.QuizProject.dto.UserDto;
//import com.QuizProject.QuizProject.service.UserService;
import com.QuizProject.QuizProject.dto.UserResponse;
import com.QuizProject.QuizProject.service.QuizService;
import com.QuizProject.QuizProject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")

public class UserController {

    UserService userService;
    QuizService quizService;

    public UserController(UserService userService, QuizService quizService) {
        this.userService = userService;
        this.quizService = quizService;
    }

    @PostMapping("create")
    public UserResponse createUser(@RequestBody UserDto userDto){
        return userService.CreateUser(userDto);
    }

    @PutMapping("update")
    public UserResponse updateUser(@RequestParam(name = "user") UserDto user, @RequestParam(name = "newUser") UserDto newUser) {
        return userService.updateUser(user,newUser);
    }

    @GetMapping("allUsers")
    public List<UserDto> fetchAllUsers(){
        return userService.fetchAllUsers();
    }

   @PutMapping("score/{id}")
    public UserResponse scoreUser(@RequestParam(name = "user") UserDto user, @RequestParam(name = "answerRequest") AnswerRequest answerRequest, @PathVariable("id") Long quizId) {
        return userService.scoreUser(user,answerRequest,quizId);
    }



//    String apiUrl = "https://quizapi.io/api/v1/questions?apiKey=YOUR_API_KEY&category=code&difficulty=Easy&limit=20&tags=HTML,JavaScript";


}
