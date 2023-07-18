package com.QuizProject.QuizProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto implements Serializable {
    private String question;
    private String correctAnswer;
    private String category;

}
