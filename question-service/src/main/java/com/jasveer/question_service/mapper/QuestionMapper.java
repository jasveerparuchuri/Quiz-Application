package com.jasveer.question_service.mapper;


import com.jasveer.question_service.dto.QuestionDto;
import com.jasveer.question_service.entity.Question;

public class QuestionMapper {

    // Entity → DTO
    public static QuestionDto toDto(Question question) {

        if (question == null) return null;

        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setQuestionTitle(question.getQuestionTittle());
        dto.setOption1(question.getOption1());
        dto.setOption2(question.getOption2());
        dto.setOption3(question.getOption3());
        dto.setOption4(question.getOption4());

        return dto;
    }
}
