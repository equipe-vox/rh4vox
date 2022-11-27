package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.dao.VagaDAO;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import br.com.rh4vox.model.Vaga;
import br.com.rh4vox.service.PopupService;
import br.com.rh4vox.service.VagaService;

public class ListJobController implements Initializable {
  @FXML
  private VBox listContainer, showJobContainer;

  @FXML
  private TextField searchText;

  @FXML
  private Button searchBtn;

  @FXML
  private ScrollPane scrollContainer;

  VagaDAO dao = new VagaDAO();

  private VagaService vagaService;

  private PopupService popupService;
    
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
    vagaService = new VagaService();
    popupService = new PopupService();

    try {
      setJobs();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void setJobs() throws SQLException {
    try {
      List<Vaga> vagas = dao.listVagas();
      
      if(vagas.size() != 0) {
        for(Vaga v:vagas) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/jobItem.fxml"));
          Parent jobItem = loader.load();
          JobItemController jobController = loader.getController();
          jobController.setShowJobContainer(showJobContainer);
          jobController.setVaga(v);

          // listContainer.getChildren().clear();
          listContainer.getChildren().add(jobItem);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void searchJobs(Event e) throws SQLException, IOException {
    listContainer.getChildren().clear();
    String query = searchText.getText();

    if(query == "" || query == " ") {
      setJobs();
    }

    List<Vaga> vagas = vagaService.listVagasByQuery(query);

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
    } else {
      popupService.popup("Não encontrado!", "Nenhuma vaga encontrada por esse nome.");
    }
  }
}