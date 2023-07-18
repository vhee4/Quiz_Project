package com.QuizProject.QuizProject.service.ServiceImpl;

import com.QuizProject.QuizProject.dto.LeaderBoardDto;
import com.QuizProject.QuizProject.dto.UserResponse;
import com.QuizProject.QuizProject.models.LeaderBoard;
import com.QuizProject.QuizProject.models.User;
import com.QuizProject.QuizProject.repository.LeaderBoardRepo;
import com.QuizProject.QuizProject.repository.UserRepo;
import com.QuizProject.QuizProject.service.LeaderBoardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    LeaderBoardRepo leaderBoardRepo;

    public LeaderBoardServiceImpl(LeaderBoardRepo leaderBoardRepo) {
        this.leaderBoardRepo = leaderBoardRepo;
    }

    @Override
    public void addUserToLeaderBoard(LeaderBoardDto leaderBoardDto) {
        boolean isExists = leaderBoardRepo.existsByUsername(leaderBoardDto.getUsername());
        if(isExists){
            LeaderBoard foundUser = leaderBoardRepo.findByUsername(leaderBoardDto.getUsername());
            foundUser.setScore(leaderBoardDto.getScore());
            leaderBoardRepo.save(foundUser);
        }else {
            LeaderBoard leaderBoard = LeaderBoard.builder()
                    .username(leaderBoardDto.getUsername())
                    .score(leaderBoardDto.getScore())
                    .build();

            leaderBoardRepo.save(leaderBoard);
        }
        }


    @Override
    public List<LeaderBoardDto> fetchAllUsersOnLeaderBoard() {

        return leaderBoardRepo.findAll().stream().map(list->LeaderBoardDto.builder()
                .username(list.getUsername())
                .score(list.getScore())
                .build())
                .sorted(Comparator.comparing(LeaderBoardDto::getScore).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

}

