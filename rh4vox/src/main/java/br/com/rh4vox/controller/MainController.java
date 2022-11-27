package br.com.rh4vox.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.UsuarioLogado;
import br.com.rh4vox.service.CandidatoService;
import br.com.rh4vox.service.UsuarioService;

public class MainController implements Initializable {
	
    @FXML
    private Label title,addJobTitle;

    @FXML
    private StackPane pageContainer;

    @FXML
    private Button homeBtn, 
        candidaciesBtn, 
        exitBtn, 
        profileBtn, 
        headerProfileBtn,
        configBtn;

    @FXML
    private ImageView addJobImage;

    @FXML
    private AnchorPane page;

    @FXML
    private ScrollPane scrollPane;

    private Usuario usuario;

    private UsuarioService usuarioService;

    private CandidatoService candidatoService;
    
    private Image home = new Image(String.valueOf(new File("/assets/icons/home.png")));
    private Image homeOrange = new Image(String.valueOf(new File("/assets/icons/home-orange.png")));
    private Image profile = new Image(String.valueOf(new File("/assets/icons/person.png")));
    private Image profileOrange = new Image(String.valueOf(new File("/assets/icons/person-orange.png")));
    private Image jobs = new Image(String.valueOf(new File("/assets/icons/jobs.png")));
    private Image jobsOrange = new Image(String.valueOf(new File("/assets/icons/jobs-orange.png")));

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        usuario = UsuarioLogado.getInstance().getUsuario();
        
        usuarioService = new UsuarioService();
        candidatoService = new CandidatoService();

        setPage("Vagas", "listJobs.fxml");
        setImage(homeOrange, homeBtn);

        try {
            headerProfileBtn.setText(candidatoService.getCandidatoByUsuario(usuario).getNome());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        homeBtn.setOnAction(event -> {
            setPage("Vagas", "listJobs.fxml");

            setImage(homeOrange, homeBtn);
            setImage(jobs, candidaciesBtn);
            setImage(profile, profileBtn);
        });

        candidaciesBtn.setOnAction(event -> {
            setPage("Candidaturas", "listCandidacies.fxml");

            setImage(home, homeBtn);
            setImage(jobsOrange, candidaciesBtn);
            setImage(profile, profileBtn);
        });

        profileBtn.setOnAction(event -> {
            setPage("Meu Perfil", "profile.fxml");

            setImage(home, homeBtn);
            setImage(jobs, candidaciesBtn);
            setImage(profileOrange, profileBtn);
        });

        headerProfileBtn.setOnAction(event -> {
            setPage("Meu Perfil", "profile.fxml");
        });

        configBtn.setOnAction(event -> {
            setPage("Configurações", "configAccount.fxml");
            setImage(home, homeBtn);
            setImage(jobs, candidaciesBtn);
            setImage(profile, profileBtn);
        });

        exitBtn.setOnAction(event -> {
            try {    	
                usuarioService.logoff();
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

            if(pageName == "Vagas" || pageName == "Candidaturas" ) {
                scrollPane.setStyle("-fx-padding: 0px;");
            } else if(pageName == "Configurações") {
                scrollPane.setStyle("-fx-padding: 20px;");
            }

            scrollPane.setContent(page); 
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