package com.example.coursework2.repository.impl;

import com.example.coursework2.exception.QuestionAlreadyExistsException;
import com.example.coursework2.exception.QuestionNotFoundException;
import com.example.coursework2.model.Question;
import com.example.coursework2.repository.QuestionRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.apache.logging.log4j.ThreadContext.isEmpty;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JavaQuestionRepositoryTest {

    private final QuestionRepository questionRepository = new JavaQuestionRepository();

    @ParameterizedTest
    @MethodSource("question1")
    public void add1Test(Question question) {
        questionRepository.add(question);
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionRepository.add(question));
        assertThat(questionRepository.getAll()).containsExactlyInAnyOrder(question);
    }

    @ParameterizedTest
    @MethodSource("question2")
    public void add2Test(String question, String answer) {
        questionRepository.add(new Question(question, answer));
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionRepository.add(new Question(question, answer));
        assertThat(questionRepository.getAll()).containsExactlyInAnyOrder(new Question(question, answer));
    }

    @ParameterizedTest
    @MethodSource("question1")
    public void removeTest(Question question) {
        questionRepository.add(question);
        questionRepository.remove(question);
        assertThat(questionRepository.getAll()).isEmpty();
        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(() -> questionRepository.remove(question));
    }

    public static Stream<Arguments> question1() {
        return Stream.of(
                Arguments.of(new Question("Question", "Answer"))
        );
    }

    public static Stream<Arguments> question2() {
        return Stream.of(
                Arguments.of("Question", "Answer")
        );
    }
}

