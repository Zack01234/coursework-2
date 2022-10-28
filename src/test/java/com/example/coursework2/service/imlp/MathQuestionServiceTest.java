package com.example.coursework2.service.imlp;

import com.example.coursework2.exception.QuestionAlreadyExistsException;
import com.example.coursework2.exception.QuestionNotFoundException;
import com.example.coursework2.model.Question;
import com.example.coursework2.service.QuestionService;
import com.example.coursework2.service.impl.JavaQuestionService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class MathQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @AfterEach
    public void cleanUp() {
        questionService.getAll().forEach(questionService::remove);
    }

    @Test
    public void addPositiveTest() {
        Question question1 = new Question("question1", "answer1");
        add(question1);
        Question question2 = new Question("question2", "answer2");
        questionService.add(question2);
        assertThat(questionService.getAll())
                .hasSize(2)
                .containsOnly(question1, question2);
    }

    @Test
    public void addNegativeTest() {
        Question question = new Question("question1", "answer1");
        add(question);
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(question));
    }

    @Test
    public void removePositiveTest() {
        Question question = new Question("question1", "answer1");
        add(question);
        questionService.remove(question);
        assertThat(questionService.getAll()).isEmpty();
    }

    @Test
    public void removeNegativeTest() {
        Question question = new Question("question1", "answer1");
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(question));
        add(question);
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("test", "test")));
    }

    @Test
    public void getRandomQuestionPositiveTest() {
        for (int i = 1; i <= 5; i++) {
            add(new Question("question" + i, "answer" + i));
        }
        AssertionsForClassTypes.assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }
    @Test
    public void getRandomQuestionNegativeTest() {
        assertThat(questionService.getAll()).isEmpty();
        AssertionsForClassTypes.assertThat(questionService.getRandomQuestion()).isNull();
    }
    private void add(Question question) {
        int sizeBefore = questionService.getAll().size();
        questionService.add(question.getQuestion(), question.getAnswer());
        assertThat(questionService.getAll())
                .hasSize(sizeBefore + 1)
                .contains(question);
    }
}
