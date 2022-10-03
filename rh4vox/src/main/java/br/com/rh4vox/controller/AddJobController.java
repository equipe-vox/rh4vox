package br.com.rh4vox.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import br.com.rh4vox.model.*;
import br.com.rh4vox.service.*;

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
		if (regimeBtn1.isSelected()) {
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

	public void maskedMonetary() {

		int salary = Integer.parseInt(salaryJobText.getText());

		int[] salaryArray = new int[salaryJobText.getText().length()];

		if (salaryArray.length == 4) {
			String stringSalary = Integer.toString(salary);
			for (int i = 0; i < salaryArray.length; i++) {
				char c_mil = stringSalary.charAt(0);
				char c_cent = stringSalary.charAt(1);
				char c_dez = stringSalary.charAt(2);

				int mil = Character.getNumericValue(c_mil);
				int cent = Character.getNumericValue(c_cent);
				int dez = Character.getNumericValue(c_dez);

				mil *= 1000;
				cent *= 100;
				dez *= 10;
				float newValue = mil + cent + dez;

				String newSalary = String.valueOf(newValue);
				salaryJobText.setText(newSalary);
				salaryJobText.positionCaret(4);

				System.out.println(salaryArray.length);
			}
		} else if (salaryArray.length == 5) {
			String stringSalary = Integer.toString(salary);
			for (int i = 0; i < salaryArray.length; i++) {
				char c_dez_mil = stringSalary.charAt(0);
				char c_mil = stringSalary.charAt(1);
				char c_cent = stringSalary.charAt(2);
				char c_dez = stringSalary.charAt(3);

				int dez_mil = Character.getNumericValue(c_dez_mil);
				int mil = Character.getNumericValue(c_mil);
				int cent = Character.getNumericValue(c_cent);
				int dez = Character.getNumericValue(c_dez);

				dez_mil *= 10000;
				mil *= 1000;
				cent *= 100;
				dez *= 10;

				float newValue = dez_mil + mil + cent + dez;

				System.out.println(newValue);

				String newSalary = String.valueOf(newValue);

				salaryJobText.setText(newSalary);
				System.out.println(salaryArray.length);

				salaryJobText.positionCaret(5);
			}
		} else if (salaryArray.length == 8) {
			String stringSalary = Integer.toString(salary);
			for (int i = 0; i < salaryArray.length; i++) {
				char c_cem_mil = stringSalary.charAt(0);
				char c_dez_mil = stringSalary.charAt(1);
				char c_mil = stringSalary.charAt(2);
				char c_cent = stringSalary.charAt(3);
				char c_dez = stringSalary.charAt(4);

				int cem_mil = Character.getNumericValue(c_cem_mil);
				int dez_mil = Character.getNumericValue(c_dez_mil);
				int mil = Character.getNumericValue(c_mil);
				int cent = Character.getNumericValue(c_cent);
				int dez = Character.getNumericValue(c_dez);

				cem_mil *= 100000;
				dez_mil *= 10000;
				mil *= 1000;
				cent *= 100;
				dez *= 10;

				float newValue = cem_mil + dez_mil + mil + cent + dez;

				String newSalary = String.valueOf(newValue);

				salaryJobText.setText(newSalary);

				salaryJobText.positionCaret(6);
			}
		}
	}
}