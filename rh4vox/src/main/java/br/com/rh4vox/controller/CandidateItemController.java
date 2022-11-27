package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Candidatura;
import br.com.rh4vox.model.CandidaturaRh;
import br.com.rh4vox.model.Vaga;
import br.com.rh4vox.service.VagaService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CandidateItemController implements Initializable {
	
  @FXML
  private Label nameLabel, objetivoLabel;
  
  @FXML
  private HBox skillContainer;

  private CandidaturaRh candidatura;

  private VagaService vagaService;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    vagaService = new VagaService();
  }

  public void setCandidatura(CandidaturaRh candidatura) throws SQLException, IOException {
    this.candidatura = candidatura;

    loadCandidate();
  }

  private void loadCandidate() throws SQLException, IOException {
    nameLabel.setText(candidatura.getNomeCand());
  
    objetivoLabel.setText(candidatura.getObjetivo());

    String [] habilidades = candidatura.getHabilidades().split(";");

    if(habilidades.length != 0) {
      for(String h:habilidades) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skillItem.fxml"));
        Parent skillItem = loader.load();
        SkillsController skillController = loader.getController();

        skillController.setSkill(h);   

        skillContainer.getChildren().add(skillItem);
      }
    }
  }
}