package br.com.rh4vox.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

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
  private HBox negociavelItem;

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
  public void setRegime(String textLabel) {
    regimeLabel.setText(textLabel);
  }
  public void setSalario(String textLabel) {
    salarioLabel.setText(String.format("R$%s", textLabel));
  }
  public void setNegociavel(Boolean negociavel) {
    if(negociavel) {
      negociavelLabel.setText("Negociável");
    } else {
      negociavelItem.setVisible(false);
    }
  }
  public void setAberto(Boolean aberto) {
    if(aberto) {
      abertoLabel.setText("Aberto");
    } else {
      abertoLabel.setText("Fechado");
    }
  }
}