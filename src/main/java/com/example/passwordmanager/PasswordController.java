package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PasswordController {
    @FXML
    PasswordField passwordInputField;
    @FXML
    TextField serviceInputField;
    @FXML
    Button generate;
    @FXML
    HBox hbox1;
    @FXML
    Text service1;
    @FXML
    PasswordField passwordField1;
    @FXML
    Text date1;
    protected void initialize() {

    }

    public void addPassword(){
      String newService = serviceInputField.getText();
      String newPassword = passwordInputField.getText();

      Password addedPassword = new Password(newPassword, newService,null,LocalDate.now(), LocalDate.now(),null);
      hbox1.setVisible(true);
      service1.setText(newService);
      passwordField1.setText(newPassword);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String dateChangedText = addedPassword.getDateCreated().format(formatter);
        date1.setText("Date changed: "+dateChangedText);
    }


}