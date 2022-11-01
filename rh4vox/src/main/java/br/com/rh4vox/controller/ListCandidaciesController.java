package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.dao.VagaDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Candidatura;
import br.com.rh4vox.model.Vaga;
import br.com.rh4vox.service.VagaService;

public class ListCandidaciesController implements Initializable {
  @FXML
  private VBox listContainer, showJobContainer;

  @FXML
  private ScrollPane scrollContainer;

  VagaDAO dao = new VagaDAO();

  private VagaService vagaService;
    
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    vagaService = new VagaService();


    try {
      setJobs();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void setJobs() throws SQLException {
    try {
      List<Candidatura> candidaturas = vagaService.listCandidaturasByCandidato(CandidatoLogado.getInstance().getCandidato().getId());

      List<Vaga> vagas = new ArrayList<Vaga>();

      for(Candidatura c:candidaturas) {
        Vaga vaga = vagaService.getVaga(c.getIdVaga());
        vagas.add(vaga);
      }
      
      if(vagas.size() != 0) {
        for(Vaga v:vagas) {
          System.out.println(v.getNome());
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/jobItem.fxml"));
          Parent jobItem = loader.load();
          JobItemController jobController = loader.getController();
          jobController.setShowJobContainer(showJobContainer);
          jobController.setVaga(v);
  
          listContainer.getChildren().add(jobItem);
        }
      }

      

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}