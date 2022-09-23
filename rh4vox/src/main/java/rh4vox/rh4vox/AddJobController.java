package rh4vox.rh4vox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import rh4vox.rh4vox.model.Vaga;
import rh4vox.rh4vox.service.VagaService;

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
  
	private VagaService vagaService;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vagaService = new VagaService();
	}	
	
	public void saveJobClick() {
		String vagaNome = nameJobText.getText();
		String vagaRegime = regimeJobText.getText();
		String vagaDescricao = descriptionJobText.getText();
		String vagaSalario = salaryJobText.getText();
		
		Vaga vaga = new Vaga(vagaNome, vagaDescricao, vagaSalario, vagaRegime);
		
		vagaService.saveJob(vaga);			

		nameJobText.clear();
		regimeJobText.clear();
		descriptionJobText.clear();
		salaryJobText.clear();
	}
}