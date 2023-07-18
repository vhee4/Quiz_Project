package com.QuizProject.QuizProject.repository;

import com.QuizProject.QuizProject.models.LeaderBoard;
import com.QuizProject.QuizProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderBoardRepo extends JpaRepository<LeaderBoard,Long> {
    boolean existsByUsername(String username);
    LeaderBoard findByUsername(String name);

}
