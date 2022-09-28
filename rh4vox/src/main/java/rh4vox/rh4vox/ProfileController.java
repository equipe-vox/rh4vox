package rh4vox.rh4vox;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.Desktop;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

public class ProfileController implements Initializable  {

  @FXML
  private Hyperlink siteLink, linkedinLink, githubLink;
  
  @FXML
  private void openLink(ActionEvent event) throws IOException, URISyntaxException {
    Desktop.getDesktop().browse(new URI("https://youtube.com"));
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
  }
}
