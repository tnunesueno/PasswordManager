package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PasswordController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}