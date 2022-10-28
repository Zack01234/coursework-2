package com.example.coursework2.service.imlp;

import com.example.coursework2.exception.IncorrectAmountOfQuestions;
import com.example.coursework2.model.Question;
import com.example.coursework2.service.ExaminerServiceImpl;
import com.example.coursework2.service.QuestionService;
import com.example.coursework2.service.impl.JavaQuestionService;
import com.example.coursework2.service.impl.MathQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;
    private final Set<Question> javaQuestion = new HashSet<>();
    private final Set<Question> mathQuestion = new HashSet<>();
    private static final List<Question> QUESTIONS = List.of(
            new Question("question1", "answer1"),
            new Question("question2", "answer2"),
            new Question("question3", "answer3"),
            new Question("question4", "answer4"),
            new Question("question5", "answer5")
    );

    @BeforeEach
    public void beforeEach() {
        when(javaQuestionService.getAll()).thenReturn(QUESTIONS);
        when(mathQuestionService.getAll()).thenReturn(QUESTIONS);
    }

    @ParameterizedTest
    @MethodSource("getQuestionsNegativeParams")
    public void getQuestionsNegative(int incorrectAmount) {
        assertThatExceptionOfType(IncorrectAmountOfQuestions.class)
                .isThrownBy(() -> examinerService.getQuestions(incorrectAmount));
    }

    @Test
    public void getQuestionsPositiveTest() {
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                QUESTIONS.get(0),
                QUESTIONS.get(1),
                QUESTIONS.get(2),
                QUESTIONS.get(3),
                QUESTIONS.get(4)
        );
        assertThat(examinerService.getQuestions(4)).containsExactly(
                QUESTIONS.get(1),
                QUESTIONS.get(2),
                QUESTIONS.get(3),
                QUESTIONS.get(4)
        );
        when(mathQuestionService.getRandomQuestion()).thenReturn(
                QUESTIONS.get(0),
                QUESTIONS.get(1),
                QUESTIONS.get(2),
                QUESTIONS.get(3),
                QUESTIONS.get(4)
        );
        assertThat(examinerService.getQuestions(4)).containsExactly(
                QUESTIONS.get(1),
                QUESTIONS.get(2),
                QUESTIONS.get(3),
                QUESTIONS.get(4)
        );
    }

    public static Stream<Arguments> getQuestionsNegativeParams() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(QUESTIONS.size() + 1),
                Arguments.of(QUESTIONS.size() + 100)
        );
    }
}
