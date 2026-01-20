package com.jasveer.question_service.controller;



import com.jasveer.question_service.dto.AnswerDto;
import com.jasveer.question_service.dto.QuestionDto;
import com.jasveer.question_service.dto.ResultDto;
import com.jasveer.question_service.entity.Question;
import com.jasveer.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public List<Question> getAllQuestion(){
        return  questionService.getQuestions();
    }

    @GetMapping("/allQuestions/{cat}")
    public List<Question> getAllQuestionByCategory(@PathVariable("cat") String category){

        return questionService.getByCategory(category.toLowerCase());
    }

    @PutMapping("/updateQuestion/{id}")
    public Question updateQuestion(
            @PathVariable Long id,
            @RequestBody Question updatedQuestion) {

        return questionService.updateQuestion(id, updatedQuestion);
    }

    @PostMapping("/addQuestion")
    public Question addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PostMapping("/addQuestions")
    public List<Question> addQuestions(@RequestBody List<Question> questions){
        return questionService.addQuestions(questions);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public Question deleteQues(@PathVariable Long id){
        return questionService.deleteQues(id);
    }

    @GetMapping("/generateQuestions")
    public  List<Integer>  getQuestions(@RequestParam String category,@RequestParam int numQ){
        return questionService.getQuestionsForQuiz(category,numQ);
    }

    @PostMapping("/getQuestions")
    public List<QuestionDto> getQuestionsByIds(@RequestBody List<Integer> questionsIds){
        return questionService.getQuestionsByIds(questionsIds);
    }

    @PostMapping("/getScore")
    public ResultDto getScore(
            @RequestBody List<AnswerDto> responses) {

        return questionService.getScore( responses);
    }


}
