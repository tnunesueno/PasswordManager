package com.example.passwordmanager;

import javafx.fxml.FXML;
import javafx.scene.Node;
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
    @FXML
    Text warningText;

    Boolean shown = false;
    public void initialize() {

        if (Password.getAllPasswords().isEmpty()) {
            try {
// only restore saved Objects ONCE
                Password.restoreData();
            } catch (Exception ex) {
                System.out.println("NO SAVED OBJECTS WERE RESTORED: " + ex);
            }

            if (Password.getAllPasswords().isEmpty()) {
                try {
                    // only import films' data if there are NO saved Objects
                 //   Password.readAllData();
                    System.out.println("DATA IMPORTED");
                } catch (Exception ex) {
                    System.out.println("DATA NOT IMPORTED: " + ex);
                }
            } else {
                System.out.println("SAVED OBJECTS RESTORED");
                // here is where I add the new hboxes

            }
        }
    }

    @FXML
    public void addPassword() throws Exception {
      // only allow user to add if both password and service are fille
        warningText.setVisible(false);
      String newService = serviceInputField.getText();
      String newPassword = passwordInputField.getText();

      if ((!newService.isEmpty())&&(newPassword.length()>6)){

      Password addedPassword = new Password(newPassword, newService,null,LocalDate.now(), LocalDate.now(),null);
      Password.allPasswords.add(addedPassword);
        Text newServiceText = new Text(newService);
        PasswordField newPasswordField =  new PasswordField();
        newPasswordField.setText(newPassword);



        TextField passwordShown = new TextField(newPassword);
          passwordShown.setManaged(false);
          passwordShown.setVisible(false);
          passwordShown.setEditable(true);

          // to edit the thing
          passwordShown.setOnAction( e -> {
           addedPassword.changePassword(addedPassword.getPassword());
          });

          passwordShown.managedProperty().bind(newShow.selectedProperty());
          passwordShown.visibleProperty().bind(newShow.selectedProperty());

          newPasswordField.managedProperty().bind(newShow.selectedProperty().not());
          newPasswordField.visibleProperty().bind(newShow.selectedProperty().not());
          passwordShown.textProperty().bindBidirectional(newPasswordField.textProperty());

          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM  dd yyyy");
          String dateChangedText = addedPassword.getDateCreated().format(formatter);

        Text newDateText = new Text(dateChangedText);

        addHboxForPassword(addedPassword);



        serviceInputField.clear();
        passwordInputField.clear();

        // how to get password from fields to delete

      } else if (serviceInputField.getText().isEmpty()) {
          warningText.setVisible(true);
          warningText.setText("Please indicate a service");
          System.out.println("Please indicate a service");
      } else if ((passwordInputField.getText().length()<=6)&&(!passwordInputField.getText().isEmpty())){
          warningText.setVisible(true);
          warningText.setText("Passwords must be at least 7 characters");
          System.out.println("Passwords must be at least 7 characters");
      } else if(passwordInputField.getText().isEmpty()){
          warningText.setVisible(true);
          warningText.setText("Please indicate a password");
          System.out.println("Please indicate a password");
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

    void deletePasswordFromArray(Password deletedPassword){
        Password.allPasswords.remove(deletedPassword);
    }

    HBox addHboxForPassword(Password hboxPw){
        CheckBox newShow = new CheckBox("Show");
        Button delete = new Button("Delete");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM  dd yyyy");
        HBox newHBox = new HBox(hboxPw.getService(),hboxPw.getPassword(), hboxPw.getHidden(), newShow, hboxPw.getDateCreated().format(formatter), delete);
        newHBox.setPrefWidth(vbox.getWidth());
        newHBox.setSpacing(20);

        delete.setOnAction(e -> {
            deletePasswordFromArray(hboxPw);
            newHBox.setVisible(false);
            System.out.println(Password.allPasswords.size());
            vbox.getChildren().remove(newHBox);
        });

        vbox.getChildren().add(newHBox);
        return newHBox;
    }
}