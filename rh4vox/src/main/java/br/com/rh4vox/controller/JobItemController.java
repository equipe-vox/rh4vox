package br.com.rh4vox.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;

public class JobItemController implements Initializable {
	
  @FXML
  private Label nomeLabel, candidatosLabel, descricaoLabel;
    

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }

  public void setNome(String textLabel) {
    nomeLabel.setText(textLabel);
  }
  public void setCandidatos(Integer num) {
    candidatosLabel.setText(String.format("%s candidatos", num));
  }
  public void setDescricao(String textLabel) {
    descricaoLabel.setText(textLabel);
  }
}