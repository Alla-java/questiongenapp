package org.skypro.questiongenapp.service;

import org.skypro.questiongenapp.model.Question;
import org.skypro.questiongenapp.model.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;  // Используем интерфейс QuestionService для получения случайных вопросов
    private final Random random;

    @Autowired
    public ExaminerServiceImpl(QuestionService questionService, Random random) {
        this.questionService = questionService;
        this.random = random;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> uniqueQuestions = new HashSet<>();  // Используем Set для хранения уникальных вопросов

        /// Проверяем, что запрашиваемое количество вопросов не превышает доступное
        List<Question> allQuestions = questionService.getAllQuestions();
        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Запрашиваемое количество вопросов превышает доступное количество");
        }

        // Получаем случайные уникальные вопросы
        while (uniqueQuestions.size() < amount) {
            // Получаем случайный вопрос с помощью метода getRandomQuestion
            Question randomQuestion = ((JavaQuestionService) questionService).getRandomQuestion();
            uniqueQuestions.add(randomQuestion);  // Добавляем вопрос в Set (он не добавит дубликаты)
        }
        return uniqueQuestions;  // Возвращаем коллекцию уникальных вопросов
    }

}