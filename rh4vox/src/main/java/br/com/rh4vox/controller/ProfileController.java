package br.com.rh4vox.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.rh4vox.model.Curriculo;
import br.com.rh4vox.service.CurriculoService;

//import java.awt.Desktop;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProfileController implements Initializable  {

  @FXML
  private Hyperlink siteLink, linkedinLink, githubLink;

  @FXML
  private TextArea bioText, objetivoText, formacaoText, experienciaText;

  @FXML
  private TextField habilidadesText;

  @FXML
  private Button saveCurriculoBtn;

  private CurriculoService curriculoService;
  
  @FXML
  private void openLink(ActionEvent event) throws IOException, URISyntaxException {
   // Desktop.getDesktop().browse(new URI("https://youtube.com"));
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    curriculoService = new CurriculoService();



    saveCurriculoBtn.setOnAction(event -> {
      saveCurriculo();
    });
  }

  private void saveCurriculo() {
    try {
      curriculoService.saveCurriculo(
        bioText.getText(), 
        objetivoText.getText(), 
        habilidadesText.getText(), 
        formacaoText.getText(), 
        experienciaText.getText()
      );
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }

  private void getCurriculo() {
    
  }
}
