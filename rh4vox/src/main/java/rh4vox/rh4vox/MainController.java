package rh4vox.rh4vox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MainController implements Initializable {
	
    @FXML
    private Label title;

    @FXML
    private StackPane pageContainer;

    @FXML
    private Button addJobBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button exitBtn;
    
    @FXML
    private Label addJobTitle;

    @FXML
    private ImageView addJobImage;

    @FXML
    private AnchorPane page;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setPage("Vagas", "listJobs.fxml");

        homeBtn.setOnMouseClicked(event -> {
            setPage("Vagas", "listJobs.fxml");
        });
        
        addJobBtn.setOnMouseClicked(event -> {
            setPage("Adicionar vaga", "addJob.fxml");
        });

        exitBtn.setOnAction(event -> {
            try {    	
                App.setRoot("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setPage(String pageName, String fxml) {
        try {
            pageContainer.setStyle("-fx-padding: 30px;");
            title.setText(pageName);
            page = FXMLLoader.load(getClass().getResource(fxml));

            if(pageName == "Vagas") {
                pageContainer.setStyle("-fx-padding: 0px;");
            } else {
                pageContainer.setStyle("-fx-padding: 30px;");
            }

            pageContainer.getChildren().removeAll();           
            pageContainer.getChildren().setAll(page);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}