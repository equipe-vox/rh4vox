package br.com.rh4vox.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.enums.StatusCandidatura;
import br.com.rh4vox.model.Candidatura;
import br.com.rh4vox.model.RH;
import br.com.rh4vox.model.Vaga;
import br.com.rh4vox.service.RHService;
import br.com.rh4vox.service.VagaService;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProfileAdmController implements Initializable  {

  @FXML
  private Label nameLabel, candidaciesLabel, jobsLabel, approvedLabel, disapprovedLabel;

  @FXML
  private HBox skillContainer;

  @FXML
  private VBox candidatesContainer;

  private RHService rhService;
  private VagaService vagaService;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    rhService = new RHService();
    vagaService = new VagaService();
    
    try {
      setRh();
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
  }

  private void setRh() throws SQLException, IOException {
    List<Vaga> vagas = vagaService.getAllVagas();
    List<Candidatura> candidaturas = vagaService.getAllCandidaturas();

    if(vagas != null) {
      if(vagas.size() == 1) {
        jobsLabel.setText(vagas.size()+" vaga");
      } else {
        jobsLabel.setText(vagas.size()+" vagas");
      }
    }

    if(candidaturas != null) {
      if(candidaturas.size() == 1) {
        candidaciesLabel.setText(candidaturas.size()+" candidatura");
      } else {
        candidaciesLabel.setText(candidaturas.size()+" candidaturas");
      }
    }
    
    List<Candidatura> approvedCandidaturas = new ArrayList<Candidatura>();
    List<Candidatura> disapprovedCandidaturas = new ArrayList<Candidatura>();

    for(Candidatura c:candidaturas) {
      if(c.getStatusCandidato() == StatusCandidatura.APROVADO) {
        approvedCandidaturas.add(c);
      } else if(c.getStatusCandidato() == StatusCandidatura.RECUSADO) {
        disapprovedCandidaturas.add(c);
      }
    }

    if(approvedCandidaturas.size() == 1) {
      approvedLabel.setText(approvedCandidaturas.size()+ " candidato");
    } else {
      approvedLabel.setText(approvedCandidaturas.size()+ " candidatos");
    }

    if(disapprovedCandidaturas.size() == 1) {
      disapprovedLabel.setText(disapprovedCandidaturas.size() + " candidato");
    } else {
      disapprovedLabel.setText(disapprovedCandidaturas.size() + " candidatos");
    }
  }

}
