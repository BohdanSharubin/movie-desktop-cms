package service;

import org.example.models.Movie;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * Валідація даних фільму перед збереженням у БД
 */
public class MovieValidator {

    /**
     * Повна валідація даних для створення/редагування фільму
     */
    public static List<String> validate(String title, LocalDate year, String country, Float rating) {
        List<String> errors = new ArrayList<>();

        if (title == null || title.trim().isEmpty()) {
            errors.add("Назва фільму є обов'язковою.");
        } else if (title.length() > 255) {
            errors.add("Назва фільму не може бути довшою за 255 символів.");
        }

        if (year == null) {
            errors.add("Рік випуску є обов'язковим.");
        } else {
            int yearValue = year.getYear();
            if (yearValue < 1888 || yearValue > Year.now().getValue() + 5) {
                errors.add("Рік має бути від 1888 до " + (Year.now().getValue() + 5) + " року.");
            }
        }

        if (country == null || country.trim().isEmpty()) {
            errors.add("Країна походження є обов'язковою.");
        } else if (country.length() > 100) {
            errors.add("Назва країни не може бути довшою за 100 символів.");
        }

        if (rating == null) {
            errors.add("Рейтинг є обов'язковим.");
        } else if (rating < 0.0f || rating > 10.0f) {
            errors.add("Рейтинг повинен бути в діапазоні від 0.0 до 10.0.");
        }

        return errors;
    }

    /**
     * Перевірка, чи дані валідні
     */
    public static boolean isValid(String title, LocalDate year, String country, Float rating) {
        return validate(title, year, country, rating).isEmpty();
    }

    /**
     * Зручний метод для валідації об'єкта Movie
     */
    public static List<String> validate(Movie movie) {
        if (movie == null) {
            return List.of("Об'єкт Movie не може бути null.");
        }
        return validate(movie.getTitle(), movie.getYear(), movie.getCountry(), movie.getRating());
    }
}
