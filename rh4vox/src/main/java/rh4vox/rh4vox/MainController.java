package rh4vox.rh4vox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {

    @FXML
    private static AnchorPane addJob;

    @FXML
    private StackPane pageContainer;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            Parent addJob = FXMLLoader.load(getClass().getResource("addJob.fxml"));
            
            pageContainer.getChildren().removeAll();
            pageContainer.getChildren().setAll(addJob);
            
        } catch (IOException ex) {
            return;
        }
    }

    public void addJob() {
           
    }
}
