package com.example.coursework2.service.impl;

import com.example.coursework2.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class JavaQuestionService extends AbstractQuestionService {

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository questionRepository) {
        super(questionRepository);
    }
}
