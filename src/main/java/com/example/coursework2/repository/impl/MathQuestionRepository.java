package com.example.coursework2.repository.impl;

import com.example.coursework2.model.Question;
import com.example.coursework2.repository.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository extends AbstractQuestionRepository {
    @Override
    public void init() {
        add(new Question("Math Вопрос 1","Math Ответ 1"));
        add(new Question("Math Вопрос 2","Math Ответ 2"));
        add(new Question("Math Вопрос 3","Math Ответ 3"));
        add(new Question("Math Вопрос 4","Math Ответ 4"));
        add(new Question("Math Вопрос 5","Math Ответ 5"));
    }
}
