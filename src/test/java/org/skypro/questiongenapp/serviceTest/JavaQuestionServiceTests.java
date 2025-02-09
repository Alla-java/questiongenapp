package org.skypro.questiongenapp.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skypro.questiongenapp.model.Question;
import org.skypro.questiongenapp.model.Random;
import org.skypro.questiongenapp.service.JavaQuestionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JavaQuestionServiceTests {
    @Mock
    private Random mockRandom;;
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    public void setUp() {
        // Мокаем класс Random
        mockRandom = Mockito.mock(Random.class);
        javaQuestionService = new JavaQuestionService();

    }

    //Тест, когда добавляем один вопрос по Java
    @Test
    public void testAddQuestion_ValidJavaQuestion() {
        Question question = new Question("Что такое Java?", "Это язык программирования");
        javaQuestionService.addQuestion("Java", question);

        List<Question> questions = javaQuestionService.getQuestionsBySubject("Java");
        assertEquals(1, questions.size());
        assertEquals("Что такое Java?", questions.get(0).getQuestion());
    }

    //Тест, когда добавляем несуществующий предмет
    @Test
    public void testAddQuestion_InvalidSubject() {
        Question question = new Question("Что такое Java?", "Это язык программирования");

        // Ожидаем исключение
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.addQuestion("Python", question));
    }

    //Тест, когда получаем вопрос по предмету = Java
    @Test
    public void testGetQuestionsBySubject_Java() {
        Question question = new Question("Что такое Java?", "Это язык программирования");
        javaQuestionService.addQuestion("Java", question);

        List<Question> questions = javaQuestionService.getQuestionsBySubject("Java");
        assertNotNull(questions);
        assertEquals(1, questions.size());
        assertEquals("Что такое Java?", questions.get(0).getQuestion());
    }

    //Тест, когда пытаемся получить вопрос по несуществующему предмету
    @Test
    public void testGetQuestionsBySubject_InvalidSubject() {
        List<Question> questions = javaQuestionService.getQuestionsBySubject("Python");
        assertNotNull(questions);
        assertTrue(questions.isEmpty());
    }

    //Тест, когда обновляем вопрос по Java
    @Test
    public void testUpdateQuestion_Valid() {
        Question question = new Question("Что такое Java?", "Это язык программирования");
        javaQuestionService.addQuestion("Java", question);

        javaQuestionService.updateQuestion("Java", "Что такое Java?", "Это классный язык");

        // Проверяем, что ответ обновился
        assertEquals("Это классный язык", question.getAnswer());
    }

    //Тест, когда пытаемся обновить несуществующий вопрос
    @Test
    public void testUpdateQuestion_QuestionNotFound() {
        Question question = new Question("Что такое Java?", "Это язык программирования");
        javaQuestionService.addQuestion("Java", question);

        // Ожидаем исключение, так как вопрос не найден для обновления
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.updateQuestion("Java", "Что такое Python?", "Это язык для написания скриптов"));
    }

    //Тест, когда удаляем существующий вопрос по Java
    @Test
    public void testDeleteQuestion_Valid() {
        Question question = new Question("Что такое Java?", "Это язык программирования");
        javaQuestionService.addQuestion("Java", question);

        javaQuestionService.deleteQuestion("Java", "Что такое Java?");

        List<Question> questions = javaQuestionService.getQuestionsBySubject("Java");
        assertTrue(questions.isEmpty());
    }

    //Тест, когда пытаемся удалить несуществующий вопрос по Java
    @Test
    public void testDeleteQuestion_QuestionNotFound() {
        Question question = new Question("Что такое Java?", "Это язык программирования");
        javaQuestionService.addQuestion("Java", question);

        // Пытаемся удалить несуществующий вопрос
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.deleteQuestion("Java", "Что такое Python?"));
    }

    //Тест, когда пытаемся получить случайный вопрос, когда в хранилище есть вопросы
    @Test
    public void testGetRandomQuestion_Valid() {
        Question question1 = new Question("Что такое Java?", "Это язык программирования");
        Question question2 = new Question("Что такое JVM?", "Это Java Virtual Machine");
        javaQuestionService.addQuestion("Java", question1);
        javaQuestionService.addQuestion("Java", question2);

        // Мокаем вызов getRandomNumber, чтобы он всегда возвращал индекс 0
        when(mockRandom.getRandomNumber(1)).thenReturn(0);

        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertEquals("Что такое Java?", randomQuestion.getQuestion());
    }

    //Тест, когда пытаемся получить случайный вопрос, когда список пуст
    @Test
    public void testGetRandomQuestion_EmptyList() {
        assertThrows(IllegalStateException.class, () -> javaQuestionService.getRandomQuestion());
    }
}
