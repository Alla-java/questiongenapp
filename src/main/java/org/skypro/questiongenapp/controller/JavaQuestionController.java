package org.skypro.questiongenapp.controller;

import org.skypro.questiongenapp.model.Question;
import org.skypro.questiongenapp.service.JavaQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    @Autowired
    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    // Метод для добавления вопроса
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question newQuestion) {
        javaQuestionService.addQuestion("Java", newQuestion);
        return ResponseEntity.ok("Вопрос добавлен успешно");
    }

    // Метод для удаления вопроса
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeQuestion(@RequestParam String question) {
        javaQuestionService.deleteQuestion("Java", question);
        return ResponseEntity.ok("Вопрос удален успешно");
    }

    // Метод для получения всех вопросов
    @GetMapping("/find")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = javaQuestionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }
}

























/*@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    @Autowired
    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    // Метод для добавления вопроса
    @PostMapping("/add")
    public String addQuestion(@RequestParam String question, @RequestParam String answer) {
        Question newQuestion = new Question(question, answer);
        javaQuestionService.addQuestion("Java", newQuestion);
        return "Вопрос добавлен успешно";
    }

    // Метод для удаления вопроса
    @DeleteMapping("/remove")
    public String removeQuestion(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.deleteQuestion("Java", question);
        return "Вопрос удален успешно";
    }

    // Метод для получения всех вопросов
    @GetMapping("/find")
    public List<Question> getAllQuestions() {
        return javaQuestionService.getAllQuestions();
    }
}*/
