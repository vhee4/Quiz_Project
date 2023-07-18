package com.QuizProject.QuizProject.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@lombok.Data
@Builder
public class UserResponse implements Serializable {
    private String responseCode;
    private String responseMessage;
}
