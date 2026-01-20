package com.jasveer.quiz_service.service;



import com.jasveer.quiz_service.dto.AnswerDto;
import com.jasveer.quiz_service.dto.QuestionDto;
import com.jasveer.quiz_service.dto.ResultDto;
import com.jasveer.quiz_service.entity.Quiz;
import com.jasveer.quiz_service.feign.Quizfeign;
import com.jasveer.quiz_service.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private Quizfeign quizFeign;

    //  CREATE QUIZ
    public String createQuiz(String category, int numQ, String title) {

        // call Question Service
        List<Integer> questionIds = quizFeign.getQuestions(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);

        quizRepo.save(quiz);
        return "Quiz created successfully!";
    }

    //  GET QUESTIONS FOR QUIZ
    public List<QuestionDto> getQuestionsById(Long quizId) {

        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // call Question Service to fetch questions
        return quizFeign.getQuestionsByIds(quiz.getQuestionIds());
    }

    // 3 CALCULATE RESULT (DELEGATE)
    public ResultDto calculateResult(Long quizId, List<AnswerDto> answers) {

        // Quiz Service does NOT calculate score
        // It delegates to Question Service
        return quizFeign.getScore(answers);
    }
}

