package com.example.coursework2.service.impl;

import com.example.coursework2.exception.QuestionAlreadyExistsException;
import com.example.coursework2.exception.QuestionNotFoundException;
import com.example.coursework2.model.Question;
import com.example.coursework2.repository.QuestionRepository;
import com.example.coursework2.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService extends AbstractQuestionService {

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository questionRepository) {
        super(questionRepository);
    }
}
