package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import br.com.rh4vox.App;
import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.UsuarioLogado;
import br.com.rh4vox.service.PopupService;
import br.com.rh4vox.service.UsuarioService;


public class LoginController implements Initializable {
    @FXML
    private Button loginBtn, signUpBtn;
    
    @FXML
    private Label text;

    @FXML
    private TextField emailText, senhaText;

    private UsuarioService loginService;

    private PopupService popupService;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        text.setWrapText(true);

        loginService = new UsuarioService();

        popupService = new PopupService();

        loginBtn.setOnAction(event -> {
            

            if(emailText.getText().isEmpty() || senhaText.getText().isEmpty()) {
                popupService.popupEmptyInput();
            } else {
                try {

                    Usuario usuario = loginService.login(emailText.getText(), senhaText.getText());

                    if(usuario != null) {
                        String targetScreen = null;

                        if(usuario.getTipo() == TipoUsuario.CANDIDATO) {
                            targetScreen = "main";
                        } else if(usuario.getTipo() == TipoUsuario.ADM) {
                            targetScreen = "mainAdm";
                        } else if(usuario.getTipo() == TipoUsuario.RH) {
                            targetScreen = "mainRH";
                        }

                        UsuarioLogado.getInstance().setUsuario(usuario);

                        try {
                            App.setRoot(targetScreen);
                            this.popupService.popupLogin();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        popupService.popupIncorrectEmailOrPass();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                }
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
