package com.QuizProject.QuizProject.service;

import com.QuizProject.QuizProject.dto.LeaderBoardDto;
import com.QuizProject.QuizProject.dto.UserResponse;
import com.QuizProject.QuizProject.models.User;

import java.util.List;

public interface LeaderBoardService {
    void addUserToLeaderBoard(LeaderBoardDto leaderBoardDto);
    List<LeaderBoardDto> fetchAllUsersOnLeaderBoard();
}
