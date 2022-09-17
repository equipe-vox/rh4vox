package rh4vox.rh4vox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SignUpController implements Initializable  {
    @FXML
    private Button loginBtn;

    @FXML
    private Button signupBtn;

    @FXML
    private Label text;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        text.setWrapText(true);

        loginBtn.setOnAction(event -> {
            try {
                App.setRoot("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        signupBtn.setOnAction(event -> {
            try {
                App.setRoot("main");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
