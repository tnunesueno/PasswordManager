package com.example.passwordmanager;

import javax.imageio.ImageIO;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Password implements Serializable {
    private String password;
    private String service;
    private String username;
    private LocalDate dateCreated;
    private LocalDate dateChanged;
    public static  ArrayList<Password> allPasswords = new ArrayList<Password>();

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

    public static ArrayList<Password> getAllPasswords() {
        return allPasswords;
    }

    public Boolean getHidden() {
        return hidden;
    }


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


    @Serial
    private void writeObject(ObjectOutputStream s) throws IOException {
        // write NON-transient fields
        s.defaultWriteObject();
        // write transient fields
        }

    @Serial
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        // read NON-transient fields
        s.defaultReadObject();
        // read transient fields
    }

    static void saveData() throws Exception {
        FileOutputStream fileOut = new FileOutputStream("SavedAlbumsFile");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(allPasswords);
        objectOut.close();
        fileOut.close();
    }

    static void restoreData() throws Exception {
        FileInputStream fileIn = new FileInputStream("SavedAlbumsFile");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        allPasswords = (ArrayList<Password>)objectIn.readObject();
        objectIn.close();
        fileIn.close();
    }

   public void changePassword(String newPassword){
        this.setPassword(newPassword);
        LocalDate date = LocalDate.now();
        setDateChanged(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        String dateChangedText = dateChanged.format(formatter);
        LocalDate parsedDate = LocalDate.parse(dateChangedText, formatter);
    }

}
