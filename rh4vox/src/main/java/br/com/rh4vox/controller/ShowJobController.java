package br.com.rh4vox.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rh4vox.model.Vaga;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ShowJobController implements Initializable {
  
  private Vaga vaga;

  @FXML
  private Label nomeLabel,
    descricaoLabel,
    salarioLabel,
    regimeLabel,
    abertoLabel,
    negociavelLabel;

  @FXML
  private HBox negociavelItem;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
  }
  
  public void setJob(Vaga vaga) {
    this.vaga = vaga;
    loadJob();
  }
  
  private void loadJob() {
    nomeLabel.setText(vaga.getNome());
    descricaoLabel.setText(vaga.getDescricao());
    regimeLabel.setText(vaga.getRegime().toString());
    salarioLabel.setText(String.format("R$%s", vaga.getSalario()));
    
    if(vaga.getNegociavel()) {
      negociavelLabel.setText("Negociável");
    } else {
      negociavelItem.setVisible(false);
      
    }
  
    if(vaga.getAberto()) {
      abertoLabel.setText("Aberto");
    } else {
      abertoLabel.setText("Fechado");
    }
  }
}
