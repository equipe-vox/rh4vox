package br.com.rh4vox.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import br.com.rh4vox.App;
import br.com.rh4vox.enums.Regime;
import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.UsuarioLogado;
import br.com.rh4vox.service.*;

public class AddJobController implements Initializable {
	
	@FXML
	private Button saveJobBtn;

	@FXML
	private TextField nameJobText, salaryJobText, cargoText;

	@FXML
	private TextArea descriptionJobText;

	@FXML
	private RadioButton regimeBtn1, regimeBtn2, regimeBtn3, negociavelBtn;

	private VagaService vagaService;

	private PopupService popupService;

	private Regime regime;

	private BigDecimal salario;

	final ToggleGroup group = new ToggleGroup();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		vagaService = new VagaService();

		popupService = new PopupService();

		salaryJobText.focusedProperty().addListener((ov, oldV, newV) -> {
      if (!newV) {
				Double number = Double.parseDouble(salaryJobText.getText());
				salario = new BigDecimal(salaryJobText.getText());
		
				NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
				String currency = format.format(number);
	
				format = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
				currency = format.format(number);
	
				salaryJobText.setText(String.format(currency));
      }
    });
	}

	public void createVaga() throws IOException {
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
				nameJobText.getText().isEmpty() ||
				descriptionJobText.getText().isEmpty() ||
				salario == null ||
				regime == null ||
				cargoText.getText().isEmpty()
			) {
				popupService.popupEmptyInput();
			}

			if(negociavelBtn.isSelected() == false) {
				vagaService.cadastroVaga(
					nameJobText.getText(),
					descriptionJobText.getText(),
					salario,
					regime,
					false,
					true,
					cargoText.getText(),
					UsuarioLogado.getInstance().getUsuario().getId()
				);
			} else {
				vagaService.cadastroVaga(
					nameJobText.getText(),
					descriptionJobText.getText(),
					salario,
					regime,
					negociavelBtn.isSelected(),
					true,
					cargoText.getText(),
					UsuarioLogado.getInstance().getUsuario().getId()
				);
			}			

			this.popupService.popupCreateJob();
			if(UsuarioLogado.getInstance().getUsuario().getTipo() == TipoUsuario.RH) {
				App.setRoot("mainRh");
			} else {
				App.setRoot("mainAdm");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveJobClick() {
		// if (regimeBtn1.isSelected()) {
		// 	vagaRegime = regimeBtn1.getText();
		// } else if (regimeBtn2.isSelected()) {
		// 	vagaRegime = regimeBtn2.getText();
		// } else if (regimeBtn3.isSelected()) {
		// 	vagaRegime = regimeBtn3.getText();
		// }

		// String vagaNome = nameJobText.getText();

		// String vagaDescricao = descriptionJobText.getText();
		// String vagaSalario = salaryJobText.getText();

		// Vaga vaga = new Vaga(vagaNome, vagaDescricao, vagaSalario, Regime.CLT);

		// vagaService.saveJob(vaga);

		// nameJobText.clear();
		// descriptionJobText.clear();
		// salaryJobText.clear();
		// regimeBtn1.setSelected(false);
		// regimeBtn2.setSelected(false);
		// regimeBtn3.setSelected(false);
	}
}

