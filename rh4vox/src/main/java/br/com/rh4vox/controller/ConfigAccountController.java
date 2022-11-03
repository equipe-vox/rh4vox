package br.com.rh4vox.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.dao.UsuarioDAO;
import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.UsuarioLogado;
import br.com.rh4vox.service.CandidatoService;
import br.com.rh4vox.service.PopupService;
import br.com.rh4vox.service.UsuarioService;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ConfigAccountController implements Initializable  {

  @FXML
  private TextField nameText, telefoneText, emailText;

  @FXML
  private Button saveCurriculoBtn;

  private CandidatoService candidatoService;
  private UsuarioService usuarioService;
  private PopupService popupService;

  private UsuarioDAO dao = new UsuarioDAO();

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    candidatoService = new CandidatoService();
    usuarioService = new UsuarioService();
    popupService = new PopupService();
    
    try {
      setInfo();
    } catch (SQLException e) {
      e.printStackTrace();
    };

  }

  @FXML
  public void saveInfo(Event event) {
    if(
      nameText.getText() == null|| nameText.getText().trim().length() == 0 ||
      telefoneText.getText() == null || telefoneText.getText().trim().length() == 0 ||
      emailText.getText() == null || emailText.getText().trim().length() == 0
    ) {
      popupService.popup("Erro!", "Um ou mais campos não podem estar vazios.");
    } else {
      try {
        updateInfo();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void updateInfo() throws IOException {
    try {
      List<Usuario> usuarios = dao.listUsuarios();

      for(Usuario u:usuarios) {
        if(u.getId() == UsuarioLogado.getInstance().getUsuario().getId() &&
          u.getEmail() != emailText.getText()
        ) {
          usuarioService.updateUsuario(emailText.getText());
          candidatoService.updateCandidato(
            nameText.getText(), 
            telefoneText.getText(), 
            CandidatoLogado.getInstance().getCandidato().getId()
          );
          popupService.popupSaveCurriculo();
          break;
        } else {
          popupService.popup("Erro!", "Este e-mail já está em uso.");
        }
      }
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }

  private void setInfo() throws SQLException {
    Candidato candidato = candidatoService.getCandidatoByUsuario(UsuarioLogado.getInstance().getUsuario());
  
    nameText.setText(candidato.getNome());
    telefoneText.setText(candidato.getTelefone());
    emailText.setText(UsuarioLogado.getInstance().getUsuario().getEmail());
  }
}
