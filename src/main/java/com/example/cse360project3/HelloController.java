package com.example.cse360project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class HelloController {
    Integer filesCreated = 0;
    String justEmptyField = "All Fields Are Mandatory";
    String justIDShort = "ID must be 4 characters";
    String successMessage = "Application Created";
    String currentErrorMessage = justEmptyField;

    Boolean idFieldShort = false;
    Boolean emptyFieldError = false;
    @FXML
    private TextField firstNameField; //Declare all of the FXML elements that need to be written to or pulled from (Buttons do not need to be declared)
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField pNumField;
    @FXML
    private TextField sAccField;
    @FXML
    private TextField cAccField;
    @FXML
    private Label errorMessage;


    public void onSaveButtonClick(ActionEvent event) throws IOException{ // This event is assigned to be triggered when the save button is clicked. this was done in scenebuilder.
        if(validateFields()){
            String outputString = "First Name: " + firstNameField.getText() + "\nLast Name: " + lastNameField.getText() + "\nCustomer ID: " + idField.getText() + "\nAddress: " + addressField.getText() + "\nPhone Number: " + pNumField.getText() + "\nSavings Account Number: " + sAccField.getText() + "\nChecking Account Number: " + cAccField.getText();
            String filenameString = "Application#" + filesCreated.toString();
            writeToFile(filenameString, outputString);
            filesCreated++;
            updateErrorMessage();
            errorMessage.setText(currentErrorMessage);
        }else {
            updateErrorMessage();
            errorMessage.setText(currentErrorMessage);
        }
    }
    public void clearBoxes(ActionEvent event) throws IOException{
        firstNameField.clear();
        lastNameField.clear();
        idField.clear();
        pNumField.clear();
        addressField.clear();
        sAccField.clear();
        cAccField.clear();
    }
    private void updateErrorMessage(){
        if(emptyFieldError){
            currentErrorMessage = justEmptyField;
            return;
        }else if(idFieldShort){
            currentErrorMessage = justIDShort;
            return;
        }
        currentErrorMessage = successMessage;
    }
    private Boolean validateFields(){
        emptyFieldError = false;
        idFieldShort = false;
        if(!fieldEmpty(firstNameField) && !fieldEmpty(lastNameField) && !fieldEmpty(addressField) && !fieldEmpty(pNumField) && !fieldEmpty(sAccField) && !fieldEmpty(cAccField) && validateIDField()){
            return true;
        }
        return false;
    }
    private Boolean validateIDField(){
        if(fieldEmpty(idField)){
            emptyFieldError = true;
            return false;
        }else if (idField.getText().length() == 4){
            return true;
        }
        idFieldShort = true;
        return false;
    }

    private Boolean fieldEmpty(TextField checkthis){
        if(checkthis.getText().isEmpty()){
            emptyFieldError = true;
            return true;
        }
        return false;
    }

    private void writeToFile(String fileName, String textToWrite){ // creates a file with the given name and body

        try {
            FileWriter myWriter = new FileWriter(fileName + ".txt");
            myWriter.write(textToWrite);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}