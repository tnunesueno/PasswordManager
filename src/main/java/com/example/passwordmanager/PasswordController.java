package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
    @FXML
    VBox vbox;
    @FXML
    Button saveButton;
    protected void initialize() {

    }

    @FXML
    public void addPassword(){
      // only allow user to add if both password and service are filled
      if ((serviceInputField.getText()!=null)&&(passwordInputField.getText()!=null)){

      String newService = serviceInputField.getText();
      String newPassword = passwordInputField.getText();

      Password addedPassword = new Password(newPassword, newService,null,LocalDate.now(), LocalDate.now(),null);
        Text newServiceText = new Text(newService);
        PasswordField newPasswordField =  new PasswordField();
        newPasswordField.setText(newPassword);
        Button newShow = new Button("Show");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        String dateChangedText = addedPassword.getDateCreated().format(formatter);
        //LocalDate parsedDate = LocalDate.parse(dateChangedText, formatter);

        Text newDateText = new Text(dateChangedText);
        HBox newHBox = new HBox(newServiceText,newPasswordField,newShow,newDateText);
        newHBox.setPrefWidth(vbox.getWidth());
        newHBox.setSpacing(10);
        vbox.getChildren().add(newHBox);

        serviceInputField.clear();
        passwordInputField.clear();
      } else{

      }
    }

    @FXML
    void generatePassword(){
        Random random = new Random();
        int length = random.nextInt(10,15);
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<=length; i++){
            sb.append(random.nextInt(9));
            Random r = new Random();
            char c = (char)(r.nextInt(26) + 'a');
            sb.append(c);
        }// maybe throw this to an exception so only the complete version gets printed?
        String newPassword = new String(sb);
        System.out.println(newPassword);
        passwordInputField.setText(newPassword);
    }
}