package com.jasveer.quiz_service.controller;



import com.jasveer.quiz_service.dto.AnswerDto;
import com.jasveer.quiz_service.dto.QuestionDto;
import com.jasveer.quiz_service.dto.ResultDto;
import com.jasveer.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public String createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("getQuiz/{id}")
    public List<QuestionDto> getAllQuestions(@PathVariable Long id) {
        return quizService.getQuestionsById(id);
    }

    @PostMapping("/submit/{quizId}")
    public ResultDto submitQuiz(
            @PathVariable Long quizId,
            @RequestBody List<AnswerDto> answers) {

        return quizService.calculateResult(quizId, answers);
    }



}
