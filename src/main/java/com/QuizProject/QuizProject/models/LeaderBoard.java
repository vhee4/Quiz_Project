package com.QuizProject.QuizProject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaderBoard {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private Double score;
}
