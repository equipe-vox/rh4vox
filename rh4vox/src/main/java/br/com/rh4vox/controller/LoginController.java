package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import br.com.rh4vox.App;


public class LoginController implements Initializable {
    @FXML
    private Button loginBtn;

    @FXML
    private Button signUpBtn;
    
    @FXML
    private Label text;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        text.setWrapText(true);


        loginBtn.setOnAction(event -> {
            try {
                App.setRoot("main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signUpBtn.setOnAction(event -> {
            try {
                App.setRoot("signup");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
