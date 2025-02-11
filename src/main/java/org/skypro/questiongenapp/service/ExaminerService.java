package org.skypro.questiongenapp.service;

import java.util.Collection;
import org.skypro.questiongenapp.model.Question;

public interface ExaminerService {
    // Метод для получения случайных вопросов
    Collection<Question> getQuestions(int amount);
}
