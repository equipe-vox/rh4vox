package br.com.rh4vox.controller;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.service.PopupService;
import br.com.rh4vox.service.UsuarioService;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddRhController implements Initializable  {
  @FXML
  private Button saveBtn;

  @FXML
  private TextField nomeText, cpfText, emailText, senhaText;

  @FXML
  private RadioButton tipoBtn1, tipoBtn2;

  private UsuarioService usuarioService;

  private PopupService popupService;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    usuarioService = new UsuarioService();
    popupService = new PopupService();
  }

  @FXML
  public void createRH(Event event) {
    if(
        emailText.getText().isEmpty() ||
        senhaText.getText().isEmpty() ||
        nomeText.getText().isEmpty() ||
        cpfText.getText().isEmpty()
      ) {
        popupService.popupEmptyInput();
      } else {
        try {
          Usuario emailAlreadyInUse = usuarioService.emailAlreadyInUse(emailText.getText());

          if(emailAlreadyInUse != null) {
            popupService.popupEmailAlreadyInUse();
            emailText.setText("");
          } else if(senhaText.getText().length() >= 6 && senhaText.getText().length() <= 20) {
            TipoUsuario tipo = TipoUsuario.RH;

            if(tipoBtn1.isSelected() == true && tipoBtn2.isSelected() == true) {
              popupService.popup("Erro!", "Selecione apenas um tipo de usuário.");
            } else if (tipoBtn1.isSelected() == false && tipoBtn2.isSelected() == false) {
              popupService.popup("Erro!", "Um ou mais campos não podem estar vazios.");
            } else if (tipoBtn1.isSelected()) {
              tipo = TipoUsuario.ADM;
            } else if (tipoBtn2.isSelected()) {
              tipo = TipoUsuario.RH;
            } 
            try {
              Usuario usuario = usuarioService.cadastroRH(emailText.getText(), senhaText.getText(), nomeText.getText(), tipo, cpfText.getText());

              if(usuario != null) {
                popupService.popup("Sucesso!", String.format("%s criado com sucesso!", usuario.getTipo()));
              
                nomeText.setText("");
                emailText.setText("");
                senhaText.setText("");
                cpfText.setText("");
                tipoBtn1.setSelected(false);
                tipoBtn2.setSelected(false);
              }
            } catch (SQLException e) {
              e.printStackTrace();
            } catch (NoSuchAlgorithmException e1) {
              e1.printStackTrace();
            }
          } else {
            popupService.popupPassword();
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
  }
}