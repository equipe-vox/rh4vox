package rh4vox.rh4vox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class AddJobController implements Initializable {

  @FXML
  private TextArea descInput;
  
  
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
      descInput.setWrapText(true);
      
  }

  public void setMainController(MainController mainController) {
	  
  }
  
}