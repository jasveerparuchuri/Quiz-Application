package com.jasveer.quiz_service.feign;

import com.jasveer.quiz_service.dto.AnswerDto;
import com.jasveer.quiz_service.dto.QuestionDto;
import com.jasveer.quiz_service.dto.ResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface Quizfeign {
    @GetMapping("/question/generateQuestions")
    public List<Integer> getQuestions(@RequestParam String category, @RequestParam int numQ);

    @PostMapping("/question/getQuestions")
    public List<QuestionDto> getQuestionsByIds(@RequestBody List<Integer> questionsIds);

    @PostMapping("/question/getScore")
    public ResultDto getScore(@RequestBody List<AnswerDto> responses) ;
}
