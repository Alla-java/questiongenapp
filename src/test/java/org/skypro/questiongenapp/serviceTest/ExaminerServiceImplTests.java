package org.skypro.questiongenapp.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.questiongenapp.model.Random;
import org.skypro.questiongenapp.service.ExaminerServiceImpl;
import org.skypro.questiongenapp.service.QuestionService;

import org.skypro.questiongenapp.model.Question;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ExaminerServiceImplTests {
    private ExaminerServiceImpl examinerService;
    private QuestionService mockQuestionService;
    private Random mockRandom;

    @BeforeEach
    public void setUp() {
        mockQuestionService = Mockito.mock(QuestionService.class);
        mockRandom = Mockito.mock(Random.class);
        examinerService = new ExaminerServiceImpl(mockQuestionService, mockRandom);
    }

    //Тест получения уникальных случайных вопросов, где количество вопросов не превышает то которое есть в хранилище
    @Test
    public void testGetQuestions_ValidAmount() {
        // Подготовка данных
        Question question1 = new Question("Что такое Java?", "Это язык программирования");
        Question question2 = new Question("Что такое JVM?", "Это Java Virtual Machine");
        List<Question> allQuestions = Arrays.asList(question1, question2);
        when(mockQuestionService.getAllQuestions()).thenReturn(allQuestions);

        // Мокаем метод getRandomQuestion
        when(mockRandom.getRandomNumber(1)).thenReturn(0).thenReturn(1); // возвращаем индексы 0 и 1 для случайных вопросов

        Set<Question> uniqueQuestions = new HashSet<>(examinerService.getQuestions(2)); // Запрашиваем 2 вопроса

        assertEquals(2, uniqueQuestions.size()); // Проверяем, что два уникальных вопроса получены
    }

    //Тест получения уникальных случайных вопросов, где количество вопросов превышает то которое есть в хранилище
    @Test
    public void testGetQuestions_AmountExceedsAvailable() {
        // Подготовка данных
        Question question1 = new Question("Что такое Java?", "Это язык программирования");
        List<Question> allQuestions = Arrays.asList(question1);
        when(mockQuestionService.getAllQuestions()).thenReturn(allQuestions);

        // Пытаемся запросить 2 вопроса, но доступен только 1
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(2));
    }

    //Тест, когда проверяем количество уникальных вопросов
    @Test
    public void testGetQuestions_UniqueQuestions() {
        // Подготовка данных
        Question question1 = new Question("Что такое Java?", "Это язык программирования");
        Question question2 = new Question("Что такое JVM?", "Это Java Virtual Machine");
        List<Question> allQuestions = Arrays.asList(question1, question2);
        when(mockQuestionService.getAllQuestions()).thenReturn(allQuestions);

        // Мокаем метод getRandomQuestion
        when(mockRandom.getRandomNumber(1)).thenReturn(0).thenReturn(0).thenReturn(1); // Первый вопрос дважды, второй один раз

        Set<Question> uniqueQuestions = new HashSet<>(examinerService.getQuestions(2)); // Запрашиваем 2 вопроса

        assertEquals(2, uniqueQuestions.size()); // Убедимся, что количество уникальных вопросов равно 2
    }

    //Тест, когда запрашиваем ноль вопросов (amount = 0)
    @Test
    public void testGetQuestions_ZeroAmount() {
        // Подготовка данных
        Question question1 = new Question("Что такое Java?", "Это язык программирования");
        List<Question> allQuestions = Arrays.asList(question1);
        when(mockQuestionService.getAllQuestions()).thenReturn(allQuestions);

        Set<Question> uniqueQuestions = new HashSet<>(examinerService.getQuestions(0)); // Запрашиваем 0 вопросов

        assertTrue(uniqueQuestions.isEmpty()); // Проверяем, что вернулся пустой список
    }

    //Тест попытки запроса вопроса, когда в хранилище пусто
    @Test
    public void testGetQuestions_EmptyQuestionsList() {
        // Подготовка данных (список вопросов пуст)
        List<Question> allQuestions = Arrays.asList();
        when(mockQuestionService.getAllQuestions()).thenReturn(allQuestions);

        // Пытаемся запросить 1 вопрос
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(1));
    }
}
