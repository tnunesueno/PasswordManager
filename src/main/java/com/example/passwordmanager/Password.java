package com.example.passwordmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class Password {
    private String password;
    private String service;
    private String username;
    private LocalDate dateCreated;
    private LocalDate dateChanged;

   // private String serviceLink = null;
    private Boolean hidden = true;

    public Password(String password, String service, String username, LocalDate dateCreated, LocalDate dateChanged, String serviceLink) {
        this.password = password;
        this.service = service;
        this.username = username;
        this.dateCreated = dateCreated;
        this.dateChanged = dateChanged;
       // this.serviceLink = serviceLink;
    }


    // GETTERS
    public String getPassword() {
        return password;
    }

    public String getService() {
        return service;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public LocalDate getDateChanged() {
        return dateChanged;
    }

  //  public String getServiceLink() {
    //    return serviceLink;
    //}

    //SETTERS

    public void setPassword(String password) {
        this.password = password;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateChanged(LocalDate dateChanged) {
        this.dateChanged = dateChanged;
    }

    //public void setServiceLink(String serviceLink) {
     //   this.serviceLink = serviceLink;
   // }

    void changePassword(String newPassword){
       this.setPassword(newPassword);

        LocalDate date = LocalDate.now();
        setDateChanged(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        String dateChangedText = dateChanged.format(formatter);
        LocalDate parsedDate = LocalDate.parse(dateChangedText, formatter);
    }

    void generatePassword(){
        Random random = new Random();
        int length = random.nextInt(10,20);

        for(int i=0; i<=length; i++){
           StringBuilder sb = new StringBuilder();
           sb.append(random.nextInt());
           String newPassword = new String(sb);
           System.out.println(newPassword);
        }
    }
}
