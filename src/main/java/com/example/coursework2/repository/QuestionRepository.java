package com.example.coursework2.repository;

import com.example.coursework2.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    boolean add(Question question);

    boolean remove(Question question);

    Collection<Question> getAll();
}
