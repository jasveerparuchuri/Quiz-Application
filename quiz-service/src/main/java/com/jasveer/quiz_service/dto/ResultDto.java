package com.jasveer.quiz_service.dto;

import lombok.Data;

@Data
public class ResultDto {
    private int totalQuestions;
    private int correctAnswers;
    private int score;
}
