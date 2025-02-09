package org.skypro.questiongenapp.model;

public class Question {
    private String question;
    private String answer;

    // Конструктор
    public Question(String question, String answer) {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Вопрос не может быть пустым");
        }
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("Ответ не может быть пустым");
        }
        this.question = question;
        this.answer = answer;
    }

    // Геттеры и сеттеры для доступа к полям
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Вопрос не может быть пустым");
        }
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("Ответ не может быть пустым");
        }
        this.answer = answer;
    }

    // Переопределение метода toString для удобного отображения объекта
    @Override
    public String toString() {
        return "Вопрос: " + question + "\nОтвет: " + answer;
    }
}

