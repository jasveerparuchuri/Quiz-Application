package com.jasveer.quiz_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class AnswerDto {

        private Long questionId;
        private String answer;

}
