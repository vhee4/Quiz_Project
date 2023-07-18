package com.QuizProject.QuizProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizObjDto implements Serializable {
    private String question;
    private String correctAnswer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String category;
}
