package com.example.passwordmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("password.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        generatePassword();
    }

    public static void main(String[] args) {
        launch();
    }

    void generatePassword(){
        Random random = new Random();
        int length = random.nextInt(10,20);

        for(int i=0; i<=length; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(random.nextInt());
            String newPassword = new String(sb);
            System.out.println(newPassword);
        }// maybe throw this to an exception so only the complete version gets printed?
    }
}