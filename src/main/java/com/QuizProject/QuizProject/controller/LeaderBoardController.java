package com.QuizProject.QuizProject.controller;

import com.QuizProject.QuizProject.dto.LeaderBoardDto;
import com.QuizProject.QuizProject.service.LeaderBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/leaderboard")
public class LeaderBoardController {
    LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }

    @GetMapping("allUsersOnLeaderboard")
    public List<LeaderBoardDto> fetchAllUsersOnLeaderBoard() {
    return leaderBoardService.fetchAllUsersOnLeaderBoard();
    }

}
