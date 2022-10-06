package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import br.com.rh4vox.App;
import br.com.rh4vox.enums.TipoUsuario;
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
    private Button addJobBtn, homeBtn, exitBtn, profileBtn, headerProfileBtn;

    @FXML
    private ImageView addJobImage;

    @FXML
    private AnchorPane page;

    @FXML
    private ScrollPane scrollPane;

    private Usuario usuario;

    private UsuarioService usuarioService;

    private CandidatoService candidatoService;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        usuario = UsuarioLogado.getInstance().getUsuario();
        usuarioService = new UsuarioService();
        candidatoService = new CandidatoService();

        setPage("Vagas", "listJobs.fxml");

        try {
            headerProfileBtn.setText(candidatoService.getCandidatoByUsuario(usuario).getNome());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        
        

        homeBtn.setOnAction(event -> {
            setPage("Vagas", "listJobs.fxml");
        });

        profileBtn.setOnAction(event -> {
            setPage("Meu Perfil", "profile.fxml");
        });

        headerProfileBtn.setOnAction(event -> {
            setPage("Meu Perfil", "profile.fxml");
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

            if(pageName == "Vagas") {
                scrollPane.setStyle("-fx-padding: 0px;");
            } else {
                scrollPane.setStyle("-fx-padding: 20px;");
            }

            scrollPane.setContent(page); 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}