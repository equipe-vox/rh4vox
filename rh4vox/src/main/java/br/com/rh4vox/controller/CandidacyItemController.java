package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.Candidatura;
import br.com.rh4vox.model.CandidaturaRh;
import br.com.rh4vox.model.Curriculo;
import br.com.rh4vox.model.Vaga;
import br.com.rh4vox.service.VagaService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CandidacyItemController implements Initializable {
	
  @FXML
  private Label nomeLabel, objetivoLabel;
  
  @FXML
  private HBox skillContainer;

  private VBox showCandidacyContainer;

  private CandidaturaRh candidaturaRh;

  private VagaService vagaService;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    vagaService = new VagaService();
  }

  public void showCandidacy() throws IOException, SQLException {
    showCandidacyContainer.getChildren().clear();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showCandidacy.fxml"));
    Parent showCandidacyItem = loader.load();
    ShowCandidacyController showCandidacyController = loader.getController();

    showCandidacyController.setCandidacy(candidaturaRh);

    showCandidacyContainer.getChildren().add(showCandidacyItem);
  }

  public void setShowCandidacyContainer(VBox container) {
    this.showCandidacyContainer = container;
  }

  public void setCandidatura(CandidaturaRh candidaturaRh) throws SQLException, IOException {
    this.candidaturaRh = candidaturaRh;

    loadCandidato();
  }

  private void loadCandidato() throws SQLException, IOException {
    nomeLabel.setText(candidaturaRh.getNomeCand());
  
    objetivoLabel.setText(candidaturaRh.getObjetivo());

    String habilidadesStr = candidaturaRh.getHabilidades();

    String [] habilidades = habilidadesStr.split(";");

      if(habilidades.length != 0) {
        System.out.println(habilidades);
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