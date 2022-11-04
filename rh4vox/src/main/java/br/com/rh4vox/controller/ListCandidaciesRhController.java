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
import br.com.rh4vox.model.CandidaturaRh;
import br.com.rh4vox.model.UsuarioLogado;
import br.com.rh4vox.service.VagaService;

public class ListCandidaciesRhController implements Initializable {
  @FXML
  private VBox listContainer, showCandidacyContainer;

  @FXML
  private ScrollPane scrollContainer;

  VagaDAO dao = new VagaDAO();

  private VagaService vagaService;
    
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
    vagaService = new VagaService();

    try {
      setCandidacies();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void setCandidacies() throws SQLException {
    try {
      List<CandidaturaRh> candidaturas = vagaService.listCandidaturasByRh(UsuarioLogado.getInstance().getUsuario().getId());

      
      if(candidaturas.size() != 0) {
        for(CandidaturaRh c:candidaturas) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/candidacyItem.fxml"));
          Parent candItem = loader.load();
          CandidacyItemController candController = loader.getController();
          candController.setShowCandidacyContainer(showCandidacyContainer);
          candController.setCandidatura(c);
  
          listContainer.getChildren().add(candItem);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}