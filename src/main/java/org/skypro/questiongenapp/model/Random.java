package org.skypro.questiongenapp.model;

import org.springframework.stereotype.Component;

@Component
public class Random {

    private final java.util.Random random;

    // Конструктор
    public Random() {
        this.random = new java.util.Random();
    }

    // Метод для получения случайного числа от 0 до max (включительно)
    public int getRandomNumber(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Максимальное значение должно быть больше 0.");
        }
        return random.nextInt(max + 1); // Генерируем случайное число от 0 до max
    }
}
