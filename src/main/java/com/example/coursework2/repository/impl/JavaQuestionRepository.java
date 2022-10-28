package com.example.coursework2.repository.impl;

import com.example.coursework2.model.Question;
import com.example.coursework2.repository.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository extends AbstractQuestionRepository {
    @Override
    public void init() {
    add(new Question("Java Вопрос 1","Java Ответ 1"));
    add(new Question("Java Вопрос 2","Java Ответ 2"));
    add(new Question("Java Вопрос 3","Java Ответ 3"));
    add(new Question("Java Вопрос 4","Java Ответ 4"));
    add(new Question("Java Вопрос 5","Java Ответ 5"));
    }
}
