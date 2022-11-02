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
public class AbstractQuestionService implements QuestionService {
    public final QuestionRepository questionRepository;
    private final Random random = new Random();

    public AbstractQuestionService(@Qualifier QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questionRepository.add(question)) {
            throw new QuestionAlreadyExistsException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionRepository.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> questions = questionRepository.getAll();
        if (questions.isEmpty()) {
            return null;
        }
        return questions.stream().skip(random.nextInt(questions.size())).findFirst().orElse(null);
    }
}
