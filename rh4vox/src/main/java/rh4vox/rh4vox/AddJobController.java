package rh4vox.rh4vox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.BaseVagas;
import model.Vaga;
import service.VagaExtractor;

public class AddJobController implements Initializable {

	@FXML
	private Button saveJobBtn;
  
	@FXML
	private TextField nameJobText;
  
	@FXML
	private TextField salaryJobText;
  
	@FXML
	private TextField regimeJobText;
  
	@FXML
	private TextArea descriptionJobText;
  
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void salvaVaga() {
		String vagaNome = nameJobText.getText();
		String vagaRegime = regimeJobText.getText();
		String vagaDescricao = descriptionJobText.getText();
		String vagaSalario = salaryJobText.getText();
		
		Vaga vaga = new Vaga(vagaNome, vagaDescricao, vagaSalario, vagaRegime);
		
		BaseVagas.getInstance().addVaga(vaga);
		
		nameJobText.clear();
		regimeJobText.clear();
		descriptionJobText.clear();
		salaryJobText.clear();
	}
}