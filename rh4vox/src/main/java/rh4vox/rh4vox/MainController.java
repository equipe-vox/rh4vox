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
import model.BaseVagas;
import service.VagaExtractor;

public class MainController implements Initializable {
	
    @FXML
    private Label title;

    @FXML
    private StackPane pageContainer;

    @FXML
    private Button addJobBtn;

    @FXML
    private Button exitBtn;
    
    @FXML
    private Label addJobTitle;

    @FXML
    private ImageView addJobImage;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        title.setText("Vagas");
        
        addJobBtn.setOnMouseClicked(event -> {
            try {
                title.setText("Adicionar vaga");

                AnchorPane addJob = FXMLLoader.load(getClass().getResource("addJob.fxml"));
                
                pageContainer.getChildren().removeAll();
                pageContainer.getChildren().setAll(addJob);
                
            } catch (IOException ex) {
            	ex.printStackTrace();
                return;
            }
        });

        exitBtn.setOnAction(event -> {
            try {
            	
            	// new VagaExtractor("vagas.csv").extractVagas(BaseVagas.getInstance().getVagas());
            	
                App.setRoot("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}