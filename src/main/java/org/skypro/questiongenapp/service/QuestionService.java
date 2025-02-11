package org.skypro.questiongenapp.service;

import org.skypro.questiongenapp.model.Question;

import java.util.List;

public interface QuestionService {

    // Добавить вопрос
    void addQuestion(String subject, Question question);

    // Получить все вопросы по определенному предмету
    List<Question> getQuestionsBySubject(String subject);

    // Обновить вопрос по предмету
    void updateQuestion(String subject, String questionText, String newAnswer);

    // Удалить вопрос по предмету
    void deleteQuestion(String subject, String questionText);

    // Получить все вопросы
    List<Question> getAllQuestions();
}
