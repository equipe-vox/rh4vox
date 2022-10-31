package br.com.rh4vox.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.rh4vox.enums.Regime;
import br.com.rh4vox.model.Vaga;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ShowJobAdmController implements Initializable {
  
  private Vaga vaga;
	
	@FXML
	private Button saveJobBtn;

	@FXML
	private TextField nomeText, salarioText, cargoText;

	@FXML
	private TextArea descricaoText;

	@FXML
	private RadioButton regimeBtn1, regimeBtn2, regimeBtn3, negociavelBtn;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    
  }
  
  public void setJob(Vaga vaga) {
    this.vaga = vaga;
    loadJob();
  }
  
  private void loadJob() {
    nomeText.setText(vaga.getNome());
    descricaoText.setText(vaga.getDescricao());

    if(vaga.getRegime() == Regime.CLT) {
      regimeBtn1.setSelected(true);
    } else if(vaga.getRegime() == Regime.PJ) {
      regimeBtn2.setSelected(true);
    } else {
      regimeBtn3.setSelected(true);
    }

    salarioText.setText(vaga.getSalario().toString());
    
    if(vaga.getNegociavel() == true) {
      negociavelBtn.setSelected(true);
    }
  }
}
