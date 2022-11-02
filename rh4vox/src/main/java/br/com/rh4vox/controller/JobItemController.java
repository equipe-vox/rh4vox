package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.model.Candidatura;
import br.com.rh4vox.model.Vaga;
import br.com.rh4vox.service.VagaService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class JobItemController implements Initializable {
	
  @FXML
  private Label nomeLabel,
    candidatosLabel,
    descricaoLabel,
    regimeLabel,
    salarioLabel,
    negociavelLabel,
    abertoLabel;
  
  @FXML
  private HBox negociavelItem, itemsContainer;

  private VBox showJobContainer;

  private Vaga vaga;

  private VagaService vagaService;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    vagaService = new VagaService();
  }

  public void showJob() throws IOException, SQLException {
    showJobContainer.getChildren().clear();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showJob.fxml"));
    Parent showJobItem = loader.load();
    ShowJobController showJobController = loader.getController();

    showJobController.setJob(vaga);

    showJobContainer.getChildren().add(showJobItem);
  }

  public void setShowJobContainer(VBox container) {
    this.showJobContainer = container;
  }

  public void setVaga(Vaga vaga) throws SQLException {
    this.vaga = vaga;

    loadJob();
  }

  private void loadJob() throws SQLException {
    nomeLabel.setText(vaga.getNome());
  
    candidatosLabel.setText(String.format("0 candidatos"));

    descricaoLabel.setText(vaga.getDescricao());

    regimeLabel.setText(vaga.getRegime().toString());

    salarioLabel.setText(String.format("R$%s", vaga.getSalario()));
    
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
    
  }
}