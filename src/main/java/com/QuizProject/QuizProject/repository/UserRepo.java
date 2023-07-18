package com.QuizProject.QuizProject.repository;

import com.QuizProject.QuizProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByName(String name);
    User findByName(String name);
}
