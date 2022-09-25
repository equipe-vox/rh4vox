package rh4vox.rh4vox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import rh4vox.rh4vox.model.Vaga;
import rh4vox.rh4vox.service.VagaService;

public class AddJobController implements Initializable {

	@FXML
	private Button saveJobBtn;
  
	@FXML
	private TextField nameJobText, salaryJobText;
  
	@FXML
	private TextArea descriptionJobText;

	@FXML
	private RadioButton regimeBtn1, regimeBtn2, regimeBtn3;
  
	private VagaService vagaService;

	private String vagaRegime;

	final ToggleGroup group = new ToggleGroup();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vagaService = new VagaService();
	}	
	
	public void saveJobClick() {
		if(regimeBtn1.isSelected()) {
			vagaRegime = regimeBtn1.getText();
		} else if (regimeBtn2.isSelected()) {
			vagaRegime = regimeBtn2.getText();
		} else if (regimeBtn3.isSelected()) {
			vagaRegime = regimeBtn3.getText();
		}
 
		String vagaNome = nameJobText.getText();

		String vagaDescricao = descriptionJobText.getText();
		String vagaSalario = salaryJobText.getText();
		
		Vaga vaga = new Vaga(vagaNome, vagaDescricao, vagaSalario, vagaRegime);
		
		vagaService.saveJob(vaga);			

		nameJobText.clear();
		descriptionJobText.clear();
		salaryJobText.clear();
		regimeBtn1.setSelected(false);
		regimeBtn2.setSelected(false);
		regimeBtn3.setSelected(false);
	}
}