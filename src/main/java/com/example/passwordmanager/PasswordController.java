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
import java.util.Optional;
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

    public void initialize() throws Exception {
        Password masterPw = new Password("",null,null,null,null,null);
        //Password.allPasswords.add(masterPw);

        /*if (masterPw.getPassword().isEmpty()){
            signIn.setHeaderText("Set master password");
            masterPw.setPassword(signIn.getEditor().getText());
        }
        if (signIn.getEditor().getText().equals(masterPw.getPassword())){
            signIn.close();
        }*/

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
                for (Password i: Password.getAllPasswords()) {
                    addHboxForPassword(i);
                }
            }
        }
    }

    @FXML
    public void addPassword() throws Exception {
      // only allow user to add if both password and service are fille
        warningText.setVisible(false);
      String newService = serviceInputField.getText();
      String newPassword = passwordInputField.getText();

      LocalDate date = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
      String dateChangedText = date.format(formatter);

      if ((!newService.isEmpty())&&(newPassword.length()>6)){

      Password addedPassword = new Password(newPassword, newService,null, dateChangedText, dateChangedText,null);
      Password.allPasswords.add(addedPassword);
      addHboxForPassword(addedPassword);

        serviceInputField.clear();
        passwordInputField.clear();

      } else if (serviceInputField.getText().isEmpty()) {
          warningText.setVisible(true);
          warningText.setText("Please indicate a service");
          System.out.println("Please indicate a service");
          serviceInputField.requestFocus();
      } else if ((passwordInputField.getText().length()<=6)&&(!passwordInputField.getText().isEmpty())){
          warningText.setVisible(true);
          warningText.setText("Passwords must be at least 7 characters");
          System.out.println("Passwords must be at least 7 characters");
          passwordInputField.requestFocus();
      } else if(passwordInputField.getText().isEmpty()){
          warningText.setVisible(true);
          warningText.setText("Please add a password");
          System.out.println("Please add a password");
          passwordInputField.requestFocus();
      }

    }

    @FXML
    void generatePassword(){
        Random random = new Random();
        int length = random.nextInt(7,10);
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
// make hbox spacing prettier
    HBox addHboxForPassword(Password hboxPw) throws Exception {
        CheckBox newShow = new CheckBox("Show");
        Button delete = new Button("Delete");
       // LocalDate dateCreated = LocalDate.now();
       // hboxPw.setDateCreated(dateCreated);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
        Text newDateText = new Text(hboxPw.getDateCreated());

        Text newServiceText = new Text(hboxPw.getService());
        PasswordField newPasswordField =  new PasswordField();
        newPasswordField.setText(hboxPw.getPassword());

        TextField passwordShown = new TextField(hboxPw.getPassword());
        passwordShown.setManaged(false);
        passwordShown.setVisible(false);
        passwordShown.setEditable(true);

        HBox newHBox = new HBox(newServiceText,newPasswordField, passwordShown, newShow,newDateText, delete);
        newHBox.setPrefWidth(vbox.getWidth());
        newHBox.setSpacing(20);

        // to edit the thing
        passwordShown.setOnAction( e -> {
            hboxPw.changePassword(hboxPw.getPassword());
        });

        passwordShown.managedProperty().bind(newShow.selectedProperty());
        passwordShown.visibleProperty().bind(newShow.selectedProperty());

        newPasswordField.managedProperty().bind(newShow.selectedProperty().not());
        newPasswordField.visibleProperty().bind(newShow.selectedProperty().not());
        passwordShown.textProperty().bindBidirectional(newPasswordField.textProperty());

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