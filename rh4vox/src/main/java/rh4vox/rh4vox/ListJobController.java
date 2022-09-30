package rh4vox.rh4vox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class ListJobController implements Initializable {
   
    @FXML
    private ScrollPane scrollContainer;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        scrollContainer.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollContainer.setVbarPolicy(ScrollBarPolicy.NEVER);
      
    }
}
