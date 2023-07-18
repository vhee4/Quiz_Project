package com.QuizProject.QuizProject.service.ServiceImpl;

import com.QuizProject.QuizProject.dto.*;
import com.QuizProject.QuizProject.models.User;
//import com.QuizProject.QuizProject.repository.QuizObjRepo;
import com.QuizProject.QuizProject.repository.QuizRepo;
import com.QuizProject.QuizProject.repository.UserRepo;
import com.QuizProject.QuizProject.service.LeaderBoardService;
import com.QuizProject.QuizProject.service.UserService;
import com.QuizProject.QuizProject.utils.ResponseUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    QuizRepo quizRepo;
    //    QuizObjRepo quizObjRepo;
    LeaderBoardService leaderBoardService;

    public UserServiceImpl(UserRepo userRepo, QuizRepo quizRepo, LeaderBoardService leaderBoardService) {
        this.userRepo = userRepo;
        this.quizRepo = quizRepo;
        this.leaderBoardService = leaderBoardService;
    }

    @Override
    public UserResponse CreateUser(UserDto user) {
        boolean isExists = userRepo.existsByName(user.getName());
        if (isExists) {
            return UserResponse.builder()
                    .responseCode(ResponseUtils.UNSUCCESSFUL_CODE)
                    .responseMessage(ResponseUtils.USERNAME_NOT_FOUND_MESSAGE)
                    .build();
        }
        User user1 = User.builder()
                .name(user.getName())
                .score(0.0)
                .lives(2)
                .build();
        userRepo.save(user1);
        return UserResponse.builder()
                .responseCode(ResponseUtils.SUCCESS_CODE)
                .responseMessage(ResponseUtils.SUCCESS_MESSAGE)
                .build();
    }

    @Override
//    @CachePut(cacheNames = "user", key = "#user.id")
    public UserResponse updateUser(UserDto user, UserDto newUser) {
        User foundUser = userRepo.findByName(user.getName());
        boolean isExists = userRepo.existsByName(user.getName());
        if (!isExists) {
            return UserResponse.builder()
                    .responseCode(ResponseUtils.UNSUCCESSFUL_CODE)
                    .responseMessage(ResponseUtils.USER_NOT_FOUND_MESSAGE)
                    .build();
        }
        foundUser.setName(newUser.getName());
        userRepo.save(foundUser);
        return UserResponse.builder()
                .responseCode(ResponseUtils.SUCCESS_CODE)
                .responseMessage(ResponseUtils.SUCCESS_MESSAGE)
                .build();
    }

    @Override
//    @Cacheable(cacheNames = "user")
    public List<UserDto> fetchAllUsers() {
        return userRepo.findAll().stream()
                .map(list -> UserDto.builder()
                        .name(list.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
//    @CachePut(cacheNames = "score", key = "#user.name", condition = "user.lives > -1")
    public UserResponse scoreUser(UserDto user, AnswerRequest answerRequest, Long quizId) {
        int score = 0;
        boolean isExists = userRepo.existsByName(user.getName());
        if (!isExists) {
            return UserResponse.builder()
                    .responseCode(ResponseUtils.UNSUCCESSFUL_CODE)
                    .responseMessage(ResponseUtils.USER_NOT_FOUND_MESSAGE)
                    .build();
        }
        User foundUser = userRepo.findByName(user.getName());
        String quizAnswered = quizRepo.findById(quizId).get().getCorrectAnswer();
//        String quizAnswered1 = quizObjRepo.findById(quizId).get().getCorrectAnswer();

        if (quizAnswered.equalsIgnoreCase(answerRequest.getAnswer())) {
            foundUser.setScore(foundUser.getScore() + 10);
            userRepo.save(foundUser);
//            score++;


//            Predicate<User> scoreGreaterThan20 = found -> found.getScore()>=20;

            if (foundUser.getScore() > 0) {
                LeaderBoardDto leaderBoardDto = LeaderBoardDto.builder()
                        .username(foundUser.getName())
                        .score(foundUser.getScore())
                        .build();
                leaderBoardService.addUserToLeaderBoard(leaderBoardDto);
            }

            return UserResponse.builder()
                    .responseCode(ResponseUtils.SUCCESS_CODE)
                    .responseMessage(ResponseUtils.SUCCESS_SCORE_MESSAGE)
                    .build();

        }

        foundUser.setScore(foundUser.getScore() + 0);
        if (foundUser.getLives() > -1) {
            foundUser.setLives(foundUser.getLives() - 1);
        }
        if (foundUser.getLives() < 0) {
            return UserResponse.builder()
                    .responseCode(ResponseUtils.UNSUCCESSFUL_CODE)
                    .responseMessage("Out of lives")
                    .build();
        }

        //"Content-Type" : "Application/json"
        userRepo.save(foundUser);
        return UserResponse.builder()
                .responseCode(ResponseUtils.UNSUCCESSFUL_CODE)
                .responseMessage(ResponseUtils.UNSUCCESSFUL_SCORE_MESSAGE)
                .build();
    }


    @Override
    public String DeleteUser(Long id) {
        return null;
    }
}
