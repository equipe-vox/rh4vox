package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.dao.VagaDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import br.com.rh4vox.model.Vaga;

public class ListJobController implements Initializable {
  @FXML
  private VBox listContainer;

  @FXML
  private ScrollPane scrollContainer;

  VagaDAO dao = new VagaDAO();
    
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
    try {
      setJobs();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void setJobs() throws SQLException {
    try {
      List<Vaga> vagas = dao.listVagas();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/jobItem.fxml"));
      Parent jobItem = loader.load();
      JobItemController jobController = loader.getController();

      if(vagas.size() != 0) {
        for(Vaga v:vagas) {

          jobController.setNome(v.getNome());    
          jobController.setDescricao(v.getDescricao());
          jobController.setRegime(v.getRegime().toString());
          jobController.setSalario(v.getSalario().toString());
          jobController.setNegociavel(v.getNegociavel());
          jobController.setAberto(v.getAberto());
  
        }
        listContainer.getChildren().add(jobItem);
      }

      

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}