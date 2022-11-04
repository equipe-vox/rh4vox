package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.rh4vox.enums.StatusCandidatura;
import br.com.rh4vox.model.CandidaturaRh;
import br.com.rh4vox.service.PopupService;
import br.com.rh4vox.service.VagaService;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ShowCandidacyController implements Initializable {
  
	@FXML
	private Button aprovarBtn, removeBtn;

	@FXML
	private Label nomeLabel,
		dataNascLabel,
		telefoneLabel,
		objetivoLabel,
		formacaoLabel,
		experienciaLabel,
		nomeVagaLabel,
		cargoLabel,
		descricaoLabel,
		salarioLabel,
		negociavelLabel,
		regimeLabel;

	@FXML
	private HBox skillContainer;

	private VagaService vagaService;

	private PopupService popupService;

	private CandidaturaRh candidaturaRh;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    vagaService = new VagaService();

		popupService = new PopupService();
  }
  
  public void setCandidacy(CandidaturaRh candidaturaRh) throws SQLException, IOException {
		this.candidaturaRh = candidaturaRh;
		loadCandidacy();
	}
    
  private void loadCandidacy() throws SQLException, IOException {
		if(candidaturaRh.getStatus() == StatusCandidatura.APROVADO) {
			aprovarBtn.setDisable(true);
		} else if(candidaturaRh.getStatus() == StatusCandidatura.RECUSADO) {
			removeBtn.setDisable(true);
		}

    nomeLabel.setText(candidaturaRh.getNomeCand());
		objetivoLabel.setText(candidaturaRh.getObjetivo());
		dataNascLabel.setText(candidaturaRh.getDataNasc().toString());
		if(candidaturaRh.getTelefone() != null) {
			telefoneLabel.setText(candidaturaRh.getTelefone());
		} else {
			telefoneLabel.setText("");
		}
		formacaoLabel.setText(candidaturaRh.getFormacao());
		experienciaLabel.setText(candidaturaRh.getExperiencia());

		String habilidadesStr = candidaturaRh.getHabilidades();

    String [] habilidades = habilidadesStr.split(";");

		if(habilidades.length != 0) {
			System.out.println(habilidades);
			for(String h:habilidades) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skillItem.fxml"));
				Parent skillItem = loader.load();
				SkillsController skillController = loader.getController();

				skillController.setSkill(h);   

				skillContainer.getChildren().add(skillItem);
			}
		}

    descricaoLabel.setText(candidaturaRh.getDescricao());
		cargoLabel.setText(candidaturaRh.getCargo());

    salarioLabel.setText("R$"+candidaturaRh.getSalario().toString());
    
    if(candidaturaRh.getNegociavel() == true) {
      negociavelLabel.setText("Sim");
    } else {
			negociavelLabel.setText("Não");
		}

		regimeLabel.setText(candidaturaRh.getRegime().toString());
  }


	@FXML
	public void approveCandidacy(Event event) throws IOException, SQLException {
		vagaService.updateCandidacyStatus(candidaturaRh.getIdCandidato(), candidaturaRh.getIdVaga(), StatusCandidatura.APROVADO);
	
		popupService.popup("Sucesso!", "Status da candidatura atualizado para: "+StatusCandidatura.APROVADO);
	
		aprovarBtn.setDisable(true);
	}

	@FXML
	public void removeCandidacy(Event event) throws IOException, SQLException {
		vagaService.updateCandidacyStatus(candidaturaRh.getIdCandidato(), candidaturaRh.getIdVaga(), StatusCandidatura.RECUSADO);
	
		popupService.popup("Sucesso!", "Status da candidatura atualizado para: "+StatusCandidatura.RECUSADO);
	
		removeBtn.setDisable(true);
	}
}
