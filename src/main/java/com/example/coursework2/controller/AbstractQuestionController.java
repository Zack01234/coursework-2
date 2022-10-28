package com.example.coursework2.controller;

import com.example.coursework2.model.Question;
import com.example.coursework2.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

public abstract class AbstractQuestionController {
    private final QuestionService questionService;

    public AbstractQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Question addQuestion(String question, String answer) {
        return questionService.add(question, answer);
    }

    public Question removeQuestion(String question, String answer) {
        return questionService.remove(new Question(question, answer));
    }

    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }
}
