package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ExecutionException;

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

    Boolean shown = false;
    protected void initialize() {

    }

    @FXML
    public void addPassword() throws Exception {
      // only allow user to add if both password and service are filled
      if ((serviceInputField.getText()!=null)&&(passwordInputField.getText()!=null)){

      String newService = serviceInputField.getText();
      String newPassword = passwordInputField.getText();

      Password addedPassword = new Password(newPassword, newService,null,LocalDate.now(), LocalDate.now(),null);
        Text newServiceText = new Text(newService);
        PasswordField newPasswordField =  new PasswordField();
        newPasswordField.setText(newPassword);
        CheckBox newShow = new CheckBox("Show");

        TextField passwordShown = new TextField(newPassword);
          passwordShown.setManaged(false);
          passwordShown.setVisible(false);
          passwordShown.managedProperty().bind(newShow.selectedProperty());
          passwordShown.visibleProperty().bind(newShow.selectedProperty());


          newPasswordField.managedProperty().bind(newShow.selectedProperty().not());
          newPasswordField.visibleProperty().bind(newShow.selectedProperty().not());
          passwordShown.textProperty().bindBidirectional(newPasswordField.textProperty());


          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM  dd yyyy");
        String dateChangedText = addedPassword.getDateCreated().format(formatter);
        //LocalDate parsedDate = LocalDate.parse(dateChangedText, formatter);

        Text newDateText = new Text(dateChangedText);
        HBox newHBox = new HBox(newServiceText,newPasswordField,newShow,newDateText);

          if(passwordShown.visibleProperty().equals(true)){
            newHBox.getChildren().add(passwordShown);
          }

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