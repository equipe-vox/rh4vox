package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.rh4vox.App;
import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.exception.EmailAlreadyInUseException;
import br.com.rh4vox.exception.ValidationException;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.service.PopupService;
import br.com.rh4vox.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable  {
    @FXML
    private Button loginBtn, signupBtn;

    @FXML
    private TextField nomeText, cpfText, emailText;

    @FXML
    private PasswordField senhaText, confirmaSenhaText;

    @FXML
    private DatePicker dataText;

    @FXML
    private Label text;

    private UsuarioService usuarioService;

    private PopupService popupService;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        usuarioService = new UsuarioService();
        popupService = new PopupService();

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
                if (
                    emailText.getText().isEmpty() ||
                    senhaText.getText().isEmpty() ||
                    confirmaSenhaText.getText().isEmpty() ||
                    nomeText.getText().isEmpty() ||
                    dataText.getValue() == null ||
                    cpfText.getText().isEmpty()
                ) {
                    popupService.popupEmptyInput();
                    return;
                }

                if(senhaText.getText().equals(confirmaSenhaText.getText())) {
                    Usuario usuario = usuarioService.cadastroCandidato(emailText.getText(), senhaText.getText(), nomeText.getText(), dataText.getValue(), cpfText.getText());
                
                    if(usuario != null) {
                        String targetScreen = null;
    
                        if(usuario.getTipo() == TipoUsuario.CANDIDATO) {
                            targetScreen = "login";
                        } else if(usuario.getTipo() == TipoUsuario.ADM) {
                            targetScreen = "mainAdm";
                        } else if(usuario.getTipo() == TipoUsuario.RH) {
                            targetScreen = "mainRH";
                        }
    
                        try {
                            App.setRoot(targetScreen);
                            this.popupService.popupSignUp();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    popupService.popup("Erro!", "As senhas não conferem.");
                }
                

                
            } catch (ValidationException ve) {
                popupService.popup("Dados inválidos", ve.getMessage());
            } catch(EmailAlreadyInUseException ee){
                 popupService.popupEmailAlreadyInUse();
                emailText.setText("");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        });
    }
}
