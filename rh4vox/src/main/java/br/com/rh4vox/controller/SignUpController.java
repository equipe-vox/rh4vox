package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.rh4vox.App;
import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable  {
    @FXML
    private Button loginBtn, signupBtn;

    @FXML
    private TextField nomeText, cpfText, emailText, senhaText;

    @FXML
    private DatePicker dataText;

    @FXML
    private Label text;

    private UsuarioService usuarioService;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        usuarioService = new UsuarioService();

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
                Usuario usuario = usuarioService.cadastroCandidato(emailText.getText(), senhaText.getText(), nomeText.getText(), dataText.getValue(), cpfText.getText());

                if(usuario != null) {
                    String targetScreen = null;

                    if(usuario.getTipo() == TipoUsuario.CANDIDATO) {
                        targetScreen = "main";
                    } else if(usuario.getTipo() == TipoUsuario.ADM) {
                        targetScreen = "mainAdm";
                    } else if(usuario.getTipo() == TipoUsuario.RH) {
                        targetScreen = "mainRH";
                    }

                    try {
                        App.setRoot(targetScreen);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
