package br.com.rh4vox.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import br.com.rh4vox.App;
import br.com.rh4vox.enums.Regime;
import br.com.rh4vox.model.Candidatura;
import br.com.rh4vox.model.Vaga;
import br.com.rh4vox.service.PopupService;
import br.com.rh4vox.service.VagaService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ShowJobAdmController implements Initializable {
  
  private Vaga vaga;
	
	@FXML
	private Button saveBtn, removeBtn;

	@FXML
	private TextField nomeText, salarioText, cargoText;

	@FXML
	private TextArea descricaoText;

	@FXML
	private Label candidatosLabel;

	@FXML
	private RadioButton regimeBtn1, regimeBtn2, regimeBtn3, negociavelBtn;
  
  private Regime regime;

  private BigDecimal salario;

	private VagaService vagaService;

	private PopupService popupService;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    vagaService = new VagaService();

		popupService = new PopupService();

    salarioText.focusedProperty().addListener((ov, oldV, newV) -> {
      if (!newV) {
				Double number = Double.parseDouble(salarioText.getText());
				salario = new BigDecimal(salarioText.getText());
		
				NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
				String currency = format.format(number);
	
				format = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
				currency = format.format(number);
	
				salarioText.setText(String.format(currency));
      }
    });
  }
  
  public void setJob(Vaga vaga) throws SQLException {
    this.vaga = vaga;
    loadJob();
  }
  
  private void loadJob() throws SQLException {
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

		List<Candidatura> candidaturas = vagaService.listCandidaturasByVaga(vaga.getId());

		candidatosLabel.setText(String.format("%s candidatos", candidaturas.size()));
  }


	public void saveJob() throws IOException {
		try {
			if(regimeBtn1.isSelected() && 
				regimeBtn2.isSelected() &&
				regimeBtn3.isSelected() ||
				regimeBtn1.isSelected() && 
				regimeBtn2.isSelected() ||
				regimeBtn2.isSelected() &&
				regimeBtn3.isSelected() ||
				regimeBtn3.isSelected() && 
				regimeBtn1.isSelected()
			) {
				popupService.popup("Erro", "Selecione apenas um tipo de regime de contratação!");
			} else if(regimeBtn1.isSelected()) {
				regime = Regime.CLT;
			} else if (regimeBtn2.isSelected()) {
				regime = Regime.PJ;
			} else if (regimeBtn3.isSelected()) {
				regime = Regime.ESTAGIO;
			}

			if(
				nomeText.getText().isEmpty() ||
				descricaoText.getText().isEmpty() ||
				salarioText == null ||
				regime == null ||
				cargoText.getText().isEmpty()
			) {
				popupService.popupEmptyInput();
			} else {
        vagaService.updateVaga(
          vaga.getId(),
          nomeText.getText(),
          descricaoText.getText(),
          salario,
          regime,
          negociavelBtn.isSelected(),
          cargoText.getText()
        );
      }
      

			this.popupService.popup("Sucesso!", "Vaga atualizada com sucesso!");
			// App.setRoot("mainAdm");
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	public void removeJob() {
		try {
			vagaService.removeVaga(vaga.getId());
			this.popupService.popup("Sucesso!", "Vaga excluída com sucesso!");
			App.setRoot("mainAdm");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
