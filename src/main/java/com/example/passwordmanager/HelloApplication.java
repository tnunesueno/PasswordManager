package com.example.passwordmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("password.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Alert signIn = new Alert(Alert.AlertType.WARNING);
        signIn.setTitle("Sign in");
        signIn.setHeaderText("Master password: ");
        signIn.setGraphic(null);
        signIn.getDialogPane().setContent(new PasswordField());
        signIn.showAndWait();
       // signIn.getButtonTypes()


    }

    public static void main(String[] args) {
        launch();
    }

    public void stop() throws Exception {
        Password.saveData();
    }
}