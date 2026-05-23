package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.models.Movie;
import service.MovieValidator;

import java.time.LocalDate;
import java.util.List;

public class MovieController {

    @FXML private TextField titleField;
    @FXML private DatePicker yearPicker;
    @FXML private TextField countryField;
    @FXML private TextField ratingField;

    @FXML
    private void handleSaveMovie() {

        String title = titleField.getText();
        LocalDate year = yearPicker.getValue();
        String country = countryField.getText();
        Float rating = null;

        try {
            if (!ratingField.getText().trim().isEmpty()) {
                rating = Float.parseFloat(ratingField.getText().trim());
            }
        } catch (NumberFormatException e) {
            rating = null;
        }

        List<String> errors = MovieValidator.validate(title, year, country, rating);

        if (!errors.isEmpty()) {
            showAlert("Помилка валідації", String.join("\n", errors), Alert.AlertType.ERROR);
            return;
        }

        Movie movie = new Movie(title, year, country, rating);

        // Тут пізніше буде виклик сервісу:
        // movieService.addMovie(movie);

        System.out.println("Фільм успішно валідований і готовий до збереження:\n" + movie);

        showAlert("Успіх", "Фільм успішно додано!", Alert.AlertType.INFORMATION);

        clearForm();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearForm() {
        titleField.clear();
        yearPicker.setValue(null);
        countryField.clear();
        ratingField.clear();
    }
}
