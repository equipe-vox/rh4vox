package rh4vox.rh4vox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LoginController implements Initializable {
    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        loginBtn.setOnAction(event -> {
            try {
                App.setRoot("main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}