package org.skypro.questiongenapp.controller;

import java.util.Collection;
import org.skypro.questiongenapp.model.Question;

import org.skypro.questiongenapp.service.ExaminerService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/exam/get")
public class ExamController {

    private final ExaminerService examinerService;

    @Autowired
    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    // Метод для получения случайных вопросов с ограничением по количеству
    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }
}
