package com.jasveer.question_service.service;


import com.jasveer.question_service.dto.AnswerDto;
import com.jasveer.question_service.dto.QuestionDto;
import com.jasveer.question_service.dto.ResultDto;
import com.jasveer.question_service.entity.Question;
import com.jasveer.question_service.mapper.QuestionMapper;
import com.jasveer.question_service.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public Question updateQuestion(Long id, Question updated) {

        Question existing = questionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        existing.setQuestionTittle(updated.getQuestionTittle());
        existing.setOption1(updated.getOption1());
        existing.setOption2(updated.getOption2());
        existing.setOption3(updated.getOption3());
        existing.setOption4(updated.getOption4());
        existing.setRightAnswer(updated.getRightAnswer());
        existing.setDiffcultyLevel(updated.getDiffcultyLevel());
        existing.setCategory(updated.getCategory());

        return questionRepo.save(existing);
    }

    public List<Question> getQuestions() {
        return questionRepo.findAll();
    }

    public List<Question> getByCategory(String category) {
        return questionRepo.findByCategoryIgnoreCase(category);
    }

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    public List<Question> addQuestions(List<Question> questions) {
        return  questionRepo.saveAll(questions);
    }

    public Question deleteQues(Long id) {

        Question question = questionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        questionRepo.deleteById(id);

        return question;
    }

    public List<Integer> getQuestionsForQuiz(String category, int numQ) {

        List<Integer> questionIds =
                questionRepo.findRandomQuestionIdsByCategory(category, numQ);

        if (questionIds.size() < numQ) {
            throw new RuntimeException(
                    "Not enough questions available for category: " + category
            );
        }

        return questionIds;
    }

    public List<QuestionDto> getQuestionsByIds(List<Integer> questionIds) {

        List<Question> questions = questionRepo.findByIdIn(questionIds);

        return questions.stream()
                .map(QuestionMapper::toDto)
                .toList();
    }


    public ResultDto getScore(List<AnswerDto> responses) {

        int correct = 0;
        int total = responses.size();

        for (AnswerDto answer : responses) {

            Question question = questionRepo.findById(answer.getQuestionId())
                    .orElseThrow(() -> new RuntimeException(
                            "Question not found: " + answer.getQuestionId()));

            if (answer.getAnswer() != null &&
                    question.getRightAnswer() != null &&
                    answer.getAnswer().trim()
                            .equalsIgnoreCase(question.getRightAnswer().trim())) {

                correct++;
            }
        }

        return new ResultDto(
                total,
                correct,
                correct // score = correct answers
        );
    }


}
