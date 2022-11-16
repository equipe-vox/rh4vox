package br.com.rh4vox.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Candidatura;
import br.com.rh4vox.model.UsuarioLogado;
import br.com.rh4vox.model.Vaga;
import br.com.rh4vox.service.PopupService;
import br.com.rh4vox.service.VagaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ShowJobController implements Initializable {
  
  private Vaga vaga;

  @FXML
  private Button sendBtn;

  @FXML
  private Label nomeLabel,
    descricaoLabel,
    salarioLabel,
    regimeLabel,
    abertoLabel,
    negociavelLabel,
    candidatosLabel,
    cargoLabel;

  @FXML
  private HBox negociavelItem, itemsContainer;

  private VagaService vagaService;

  private PopupService popupService;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    vagaService = new VagaService();
    popupService = new PopupService();    
  }
  
  public void setJob(Vaga vaga) throws SQLException {
    this.vaga = vaga;
    loadJob();

    if(CandidatoLogado.getInstance().getCandidato() != null) {
      listCandidaturas();
    }

  }
  
  private void loadJob() throws SQLException {
    nomeLabel.setText(vaga.getNome());
    descricaoLabel.setText(vaga.getDescricao());
    regimeLabel.setText(vaga.getRegime().toString());
    salarioLabel.setText(String.format("R$%s", vaga.getSalario()));
    cargoLabel.setText(vaga.getCargo());
    
    if(vaga.getNegociavel()) {
      negociavelLabel.setText("Negociável");
    } else {
      negociavelItem.setVisible(false);
      itemsContainer.getChildren().remove(negociavelItem);
    }
  
    if(vaga.getAberto()) {
      abertoLabel.setText("Aberto");
    } else {
      abertoLabel.setText("Fechado");
    }

    List<Candidatura> candidaturas = vagaService.listCandidaturasByVaga(vaga.getId());

    if(candidaturas.size() > 1 || candidaturas.size() == 0 ) {
      candidatosLabel.setText(String.format("%s candidatos", candidaturas.size()));
    } else {
      candidatosLabel.setText(String.format("%s candidato", candidaturas.size()));
    }

    if(
      UsuarioLogado.getInstance().getUsuario().getTipo() == TipoUsuario.ADM ||
      UsuarioLogado.getInstance().getUsuario().getTipo() == TipoUsuario.RH
    ) {
      sendBtn.setDisable(true);
      sendBtn.setVisible(false);
    }
  }

  private void listCandidaturas() {
    try {
      List<Candidatura> candidaturas = vagaService.listVagas(vaga.getId(), CandidatoLogado.getInstance().getCandidato().getId());

      for(Candidatura c:candidaturas) {
        if(c.getIdVaga() == vaga.getId()) {
          sendBtn.setStyle("-fx-background-color: #F18524; -fx-text-fill: #ffffff");
          sendBtn.setDisable(true);

          switch (c.getStatusCandidato()) {
            case ENVIADO:
              sendBtn.setText("Candidatura enviada");
            break;
            case APROVADO:
              sendBtn.setText("Candidatura aprovada");
            break;
            case RECUSADO:
              sendBtn.setText("Candidatura recusada");
            break;
            
            default:
          }
        }
      }
    
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void sendCandidatura(ActionEvent e) throws SQLException {
    vagaService.createCandidatura(vaga.getId(), CandidatoLogado.getInstance().getCandidato().getId());

    popupService.popup("Sucesso!", "Candidatura enviada com sucesso! Aguarde atualizações.");

    sendBtn.setStyle("-fx-background-color: #F18524; -fx-text-fill: #ffffff");
    sendBtn.setDisable(true);
    sendBtn.setText("Candidatura enviada");
  }
}
