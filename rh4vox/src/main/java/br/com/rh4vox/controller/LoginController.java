package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import br.com.rh4vox.App;
import br.com.rh4vox.dao.UsuarioDAO;
import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.UsuarioLogado;
import br.com.rh4vox.service.UsuarioService;


public class LoginController implements Initializable {
    @FXML
    private Button loginBtn, signUpBtn;
    
    @FXML
    private Label text;

    @FXML
    private TextField emailText, senhaText;

    private UsuarioService loginService;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        text.setWrapText(true);

        loginService = new UsuarioService();

        loginBtn.setOnAction(event -> {
            try {
                Usuario usuario = loginService.login(emailText.getText(), senhaText.getText());

                if(usuario != null) {
                    System.out.println(usuario.getTipo());

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

        signUpBtn.setOnAction(event -> {
            try {
                App.setRoot("signup");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    
}
