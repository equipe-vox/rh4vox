package br.com.rh4vox.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.model.CandidaturaRh;
import br.com.rh4vox.model.RH;
import br.com.rh4vox.model.UsuarioLogado;

import br.com.rh4vox.service.RHService;
import br.com.rh4vox.service.VagaService;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ProfileRhController implements Initializable  {

  @FXML
  private Label nameLabel, candidaciesLabel, jobsLabel, approvedLabel;

  @FXML
  private HBox skillContainer;

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
    RH rh = rhService.getRhByUsuario(UsuarioLogado.getInstance().getUsuario());

    if(rh != null) {
      nameLabel.setText(rh.getNome());
      jobsLabel.setText(vagaService.listVagasByUsuario(UsuarioLogado.getInstance().getUsuario().getId()).size()+" vagas");
      candidaciesLabel.setText(vagaService.listCandidaturasByRh(UsuarioLogado.getInstance().getUsuario().getId()).size()+" candidaturas");
      
      List<CandidaturaRh> candidaturas = vagaService.getApprovedCandidaciesByUsuario(UsuarioLogado.getInstance().getUsuario());

      if(candidaturas != null) {
        if(candidaturas.size() <= 1) {
          approvedLabel.setText(vagaService.getApprovedCandidaciesByUsuario(UsuarioLogado.getInstance().getUsuario()).size()+" candidato");
        } else {
          approvedLabel.setText(vagaService.getApprovedCandidaciesByUsuario(UsuarioLogado.getInstance().getUsuario()).size()+" candidatos");
        }
      } else {
        approvedLabel.setText("0 candidatos");
      }
    }
  }
}
