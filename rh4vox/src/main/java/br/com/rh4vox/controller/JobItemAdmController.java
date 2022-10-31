package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.rh4vox.model.Vaga;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class JobItemAdmController implements Initializable {
	
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

  private VBox jobItemContainer, showJobContainer, showJobAdmContainer;

  private Vaga vaga;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {

  }

  public void setShowJobAdmContainer(VBox container) {
    this.showJobAdmContainer = container;
  }
  
  public void showJobAdm() throws IOException {
    showJobAdmContainer.getChildren().clear();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/showJobAdm.fxml"));
    Parent showJobItem = loader.load();
    ShowJobAdmController showJobAdmController = loader.getController();

    showJobAdmController.setJob(vaga);

    showJobAdmContainer.getChildren().add(showJobItem);
  }

  public void setShowJobContainer(VBox container) {
    this.showJobContainer = container;
  }

  public void setVaga(Vaga vaga) {
    this.vaga = vaga;

    loadJob();
  }

  private void loadJob() {
    nomeLabel.setText(vaga.getNome());
  
    candidatosLabel.setText(String.format("0 candidatos"));

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