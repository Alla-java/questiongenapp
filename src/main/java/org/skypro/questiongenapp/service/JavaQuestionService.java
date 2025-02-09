package org.skypro.questiongenapp.service;

import org.skypro.questiongenapp.model.Question;
import org.skypro.questiongenapp.model.Random;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JavaQuestionService implements QuestionService {

    // Список вопросов по Java
    private final List<Question> javaQuestions = new ArrayList<>();
    private final Random randomGenerator = new Random();

    @Override //Метод для добавления вопроса по Java
    public void addQuestion(String subject, Question question) {
        if ("Java".equalsIgnoreCase(subject)) {
            javaQuestions.add(question);
        } else {
            throw new IllegalArgumentException("Сервис работает только с вопросами по Java");
        }
    }

    @Override //Метод для получения вопросов по предмету = Java
    public List<Question> getQuestionsBySubject(String subject) {
        if ("Java".equalsIgnoreCase(subject)) {
            return javaQuestions;
        } else {
            return new ArrayList<>();  // Возвращаем пустой список для других предметов
        }
    }

    @Override //Метод для обновления вопроса по Java
    public void updateQuestion(String subject, String questionText, String newAnswer) {
        if ("Java".equalsIgnoreCase(subject)) {
            for (Question question : javaQuestions) {
                if (question.getQuestion().equals(questionText)) {
                    question.setAnswer(newAnswer);
                    return;
                }
            }
            throw new IllegalArgumentException("Вопрос не найден для обновления");
        } else {
            throw new IllegalArgumentException("Сервис работает только с вопросами по Java");
        }
    }

    @Override //Метод для удаления вопроса по Java
    public void deleteQuestion(String subject, String questionText) {
        if ("Java".equalsIgnoreCase(subject)) {
            javaQuestions.removeIf(q -> q.getQuestion().equals(questionText));
        } else {
            throw new IllegalArgumentException("Сервис работает только с вопросами по Java");
        }
    }

    @Override //Метод для получения всех вопросов
    public List<Question> getAllQuestions() {
        return javaQuestions;
    }

    // Метод для получения случайного вопроса
    public Question getRandomQuestion() {
        if (javaQuestions.isEmpty()) {
            throw new IllegalStateException("Список вопросов пуст");
        }
        // Получаем случайный индекс с помощью метода getRandomNumber
        int randomIndex = randomGenerator.getRandomNumber(javaQuestions.size() - 1);
        return javaQuestions.get(randomIndex);
    }
}
