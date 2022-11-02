package br.com.rh4vox.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import br.com.rh4vox.App;


public class MainAdmController implements Initializable {
	
  @FXML
  private Label title,addJobTitle;

  @FXML
  private StackPane pageContainer;

  @FXML
  private Button addJobBtn, 
    homeBtn, 
    jobsBtn,
    addRhBtn,
    profileBtn, 
    exitBtn;

  @FXML
  private ImageView addJobImage;

  @FXML
  private AnchorPane page;

  @FXML
  private ScrollPane scrollPane;
    
  private Image addJob = new Image(String.valueOf(new File("/assets/icons/add.png")));
  private Image addJobOrange = new Image(String.valueOf(new File("/assets/icons/add-orange.png")));
  private Image home = new Image(String.valueOf(new File("/assets/icons/home.png")));
  private Image homeOrange = new Image(String.valueOf(new File("/assets/icons/home-orange.png")));
  private Image profile = new Image(String.valueOf(new File("/assets/icons/person.png")));
  private Image profileOrange = new Image(String.valueOf(new File("/assets/icons/person-orange.png")));
  private Image addRh = new Image(String.valueOf(new File("/assets/icons/add-rh.png")));
  private Image addRhOrange = new Image(String.valueOf(new File("/assets/icons/add-rh-orange.png")));
  private Image jobs = new Image(String.valueOf(new File("/assets/icons/jobs.png")));
  private Image jobsOrange = new Image(String.valueOf(new File("/assets/icons/jobs-orange.png")));

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    setPage("Vagas", "listJobs.fxml");
        
    setImage(homeOrange, homeBtn);

    homeBtn.setOnAction(event -> {
      setPage("Vagas", "listJobs.fxml");
      
      setImage(homeOrange, homeBtn);
      setImage(addJob, addJobBtn);
      setImage(jobs, jobsBtn);
      setImage(addRh, addRhBtn);
      setImage(profile, profileBtn);
    });
    
    addJobBtn.setOnAction(event -> {
      setPage("Adicionar vaga", "addJob.fxml");

      setImage(addJobOrange, addJobBtn);
      setImage(home, homeBtn);
      setImage(jobs, jobsBtn);
      setImage(addRh, addRhBtn);
      setImage(profile, profileBtn);
    });

    jobsBtn.setOnAction(event -> {
      setPage("Minhas vagas", "listJobsAdm.fxml");

      setImage(addJob, addJobBtn);
      setImage(home, homeBtn);
      setImage(jobsOrange, jobsBtn);
      setImage(addRh, addRhBtn);
      setImage(profile, profileBtn);
    });

    addRhBtn.setOnAction(event -> {
      setPage("Adicionar RH", "addRh.fxml");

      setImage(addJob, addJobBtn);
      setImage(home, homeBtn);
      setImage(jobs, jobsBtn);
      setImage(addRhOrange, addRhBtn);
      setImage(profile, profileBtn);
    });

    profileBtn.setOnAction(event -> {
      setPage("Perfil", "profileAdm.fxml");

      setImage(addJob, addJobBtn);
      setImage(home, homeBtn);
      setImage(jobs, jobsBtn);
      setImage(addRh, addRhBtn);
      setImage(profileOrange, profileBtn);
    });

    exitBtn.setOnAction(event -> {
      try {    	
        App.setRoot("login");
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  public void setPage(String pageName, String fxml) {
    try {
      scrollPane.setStyle("-fx-padding: 30px;");
      scrollPane.setPrefViewportHeight(4000);
      title.setText(pageName);
      page = FXMLLoader.load(getClass().getResource("/fxml/" + fxml));

      if(pageName == "Vagas" || pageName == "Minhas vagas") {
        scrollPane.setStyle("-fx-padding: 0px;");
        
      } else if(pageName == "Adicionar vaga" || pageName == "Adicionar RH") {
        scrollPane.setStyle("-fx-padding: 20px;");
      }

      scrollPane.setContent(page);          
      // scrollPane.getChildren().setAll(page);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }


  public void setImage (Image image, Button button) {
    ImageView icon = new ImageView();
    icon.setImage(image);
    icon.setFitHeight(25);
    icon.setFitWidth(25);

    button.setGraphic(icon);
  }
}