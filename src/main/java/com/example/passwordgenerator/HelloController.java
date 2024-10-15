package com.example.passwordgenerator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.Console;
import java.util.Random;

public class HelloController {

    @FXML
    private TextField passwordLengthField;

    @FXML
    private CheckBox includeUpperCase;

    @FXML
    private CheckBox includeDigits;

    @FXML
    private CheckBox includeSpecialChars;

    @FXML
    private  Label generatedPassword;


    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+<>?";

    @FXML
    public void generatePassword(){
        int length =Integer.parseInt(passwordLengthField.getText());

        // Building a base string
        StringBuilder base = new StringBuilder(LOWERCASE);

        if(includeUpperCase.isSelected()){
            base.append(UPPERCASE);
        }

        if(includeDigits.isSelected()){
            base.append(DIGITS);
        }

        if(includeSpecialChars.isSelected()){
            base.append(SPECIAL);
        }


        String password = generateRandomPassword(length, base.toString());
        generatedPassword.setText(password);
    }


    private String generateRandomPassword(int length, String base){
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        for(int x=0; x<length;x++){
            int index = random.nextInt(base.length());
            password.append(base.charAt(index));
        }
        return password.toString();
    }

    @FXML
    public void copyToClipboard(){
        String output = generatedPassword.getText();
        System.out.println(output);
        if(!output.isEmpty()){
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(output);
            clipboard.setContent(content);

            // Show confirmation alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Clipboard");
            alert.setHeaderText(null);
            alert.setContentText("Password copied to clipboard!");
            alert.showAndWait();
        }
    }
}


















