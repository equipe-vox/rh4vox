package br.com.rh4vox.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Curriculo;
import br.com.rh4vox.service.CurriculoService;
import br.com.rh4vox.service.PopupService;

import java.awt.Desktop;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

import org.apache.commons.lang3.ArrayUtils;

public class ProfileController implements Initializable  {

  @FXML
  private Hyperlink siteLink, linkedinLink, githubLink;

  @FXML
  private TextArea bioText, objetivoText, formacaoText, experienciaText;

  @FXML
  private TextField habilidadesText, siteText, linkedinText, gitText;

  @FXML
  private Button saveCurriculoBtn;

  @FXML
  private Label nameLabel, bioLabel;

  @FXML
  private HBox skillContainer;

  private CurriculoService curriculoService;

  private Curriculo curriculo, c;

  private String site, linkedin,git; 

  private PopupService popupService;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    curriculoService = new CurriculoService();
    popupService = new PopupService();
    
    try {
      bioLabel.setText("Defina uma bio para seu perfil.");
      setCurriculo();
    } catch (SQLException | IOException e) {
      e.printStackTrace();
    }
    
    nameLabel.setText(CandidatoLogado.getInstance().getCandidato().getNome());
    setLink(siteLink);
    setLink(linkedinLink);
    setLink(githubLink);

    saveCurriculoBtn.setOnAction(event -> {
      if(
        bioText.getText() == null|| bioText.getText().trim().length() == 0 ||
        objetivoText.getText() == null || objetivoText.getText().trim().length() == 0 ||
        habilidadesText.getText() == null || habilidadesText.getText().trim().length() == 0 ||
        formacaoText.getText() == null || formacaoText.getText().trim().length() == 0 ||
        experienciaText.getText() == null || experienciaText.getText().trim().length() == 0
      ) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Erro!");
        alert.setHeaderText("Campos em branco");
        alert.setContentText("Para salvar o currículo, os campos não podem estar em branco");
        alert.showAndWait();
      } else {
        try {
          saveCurriculo();
        } catch (IOException e) {
          e.printStackTrace();
        }
        popupService.popupSaveCurriculo();
      }
    });

  }

  private void saveCurriculo() throws IOException {
    try {


      if(
        siteText.getText() != null || 
        siteText.getText() != "" ||
        linkedinText.getText() != null || 
        linkedinText.getText() != "" ||
        gitText.getText() != null || 
        gitText.getText() != ""
        ) {
        site = siteText.getText();
        linkedin = linkedinText.getText();
        git = gitText.getText();
      }
      
      if(
        site == null || site == "" &&
        site == null || site == "" &&
        site == null || site == ""
      ) {
        curriculoService.saveCurriculo(
          bioText.getText(), 
          objetivoText.getText(), 
          habilidadesText.getText(), 
          formacaoText.getText(), 
          experienciaText.getText(),
          null,
          null,
          null
        );
      } else {
        curriculoService.saveCurriculo(
          bioText.getText(), 
          objetivoText.getText(), 
          habilidadesText.getText(), 
          formacaoText.getText(), 
          experienciaText.getText(),
          site,
          linkedin,
          git
        );
      }

      bioLabel.setText(bioText.getText());

      if(!site.startsWith("https://")) {
        siteLink.setText("https://"+site);
      }
      if (!linkedin.startsWith("https://")) {
        linkedinLink.setText("https://"+linkedin);
      }
      if (!git.startsWith("https://")) {
        githubLink.setText("https://"+git);
      }
      String [] habilidades = habilidadesText.getText().split(";");

      if(habilidades.length != 0) {
        System.out.println(habilidades);
        for(String h:habilidades) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skillItem.fxml"));
          Parent skillItem = loader.load();
          SkillsController skillController = loader.getController();

          skillController.setSkill(h);   
  
          skillContainer.getChildren().add(skillItem);
        }
      }
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }

  private void setCurriculo() throws SQLException, IOException {
    curriculo = curriculoService.getCurriculo();

    if(curriculo != null) {
      bioText.setText(curriculo.getBio()); 
      objetivoText.setText(curriculo.getObjetivo());
      habilidadesText.setText(curriculo.getHabilidades()); 
      formacaoText.setText(curriculo.getFormacao()); 
      experienciaText.setText(curriculo.getExperiencia());
      bioLabel.setText(curriculo.getBio());
      siteText.setText(curriculo.getSite());
      linkedinText.setText(curriculo.getLinkedin());
      gitText.setText(curriculo.getGit());
      if(!siteText.getText().startsWith("https://")) {
        siteLink.setText("https://"+curriculo.getSite());
      }
      if (!linkedinText.getText().startsWith("https://")) {
        linkedinLink.setText("https://"+curriculo.getLinkedin());
      }
      if (!gitText.getText().startsWith("https://")) {
        githubLink.setText("https://"+curriculo.getGit());
      }

      String [] habilidades = curriculo.getHabilidades().split(";");
      if(
        !ArrayUtils.isEmpty(habilidades) &&
        habilidades[0].length() != 0 &&
        habilidades[0] != null &&
        habilidades[0] != ""
      ) {
        for(String h:habilidades) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skillItem.fxml"));
          Parent skillItem = loader.load();
          SkillsController skillController = loader.getController();

          skillController.setSkill(h);   
  
          skillContainer.getChildren().add(skillItem);
          
        }
      }
    } else {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Aviso!");
      alert.setHeaderText("Currículo não cadastrado");
      alert.setContentText("Você ainda não possui um currículo. Preencha os campos abaixo para salvar suas informações");
      alert.showAndWait();

      bioText.setText(""); 
      objetivoText.setText("");
      habilidadesText.setText(""); 
      formacaoText.setText(""); 
      experienciaText.setText("");
      bioLabel.setText("");
    }
  }

  @FXML
  private void openLinkSite(ActionEvent event) throws IOException, URISyntaxException {
    String link = siteLink.getText();

    if(!link.startsWith("https://")) {
      link = ("https://"+link);
    }

    Desktop.getDesktop().browse(new URI(link));
  }

  @FXML
  private void openLinkLinkedin(ActionEvent event) throws IOException, URISyntaxException {
    String link = linkedinLink.getText();

    if(!link.startsWith("https://")) {
      link = ("https://"+link);
    }

    Desktop.getDesktop().browse(new URI(link));
  }

  @FXML
  private void openLinkGit(ActionEvent event) throws IOException, URISyntaxException {
    String link = githubLink.getText();

    if(!link.startsWith("https://")) {
      link = ("https://"+link);
    }

    Desktop.getDesktop().browse(new URI(link));
  }

  private void setLink(Hyperlink linkBtn) {
    String link = linkBtn.getText();

    if(!link.startsWith("https://")) {
      link = ("https://"+link);
    }

    linkBtn.setText(link);
  }
}
