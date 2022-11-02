package com.example.coursework2.service;

import com.example.coursework2.exception.IncorrectAmountOfQuestions;
import com.example.coursework2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    private final Random random;

    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.random = new Random();
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<QuestionService> list = List.of(javaQuestionService, mathQuestionService);
        int totalQuestions=list.stream()
                .map(QuestionService::getAll)
                .mapToInt(Collection::size)
                .sum();
        if (amount <= 0 || amount > totalQuestions) {
            throw new IncorrectAmountOfQuestions();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(list.get(random.nextInt(list.size())).getRandomQuestion());
        }
        return questions;
    }
}
