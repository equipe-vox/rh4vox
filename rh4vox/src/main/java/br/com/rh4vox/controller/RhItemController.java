package br.com.rh4vox.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.rh4vox.enums.StatusCandidatura;
import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.CandidaturaRh;
import br.com.rh4vox.model.RH;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.Vaga;
import br.com.rh4vox.service.UsuarioService;
import br.com.rh4vox.service.VagaService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class RhItemController implements Initializable {
	
  @FXML
  private Label nameLabel, objetivoLabel;
  
  @FXML
  private HBox skillContainer;

  private RH rh;

  private VagaService vagaService;
  private UsuarioService usuarioService;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    vagaService = new VagaService();
    usuarioService = new UsuarioService();
  }

  public void setRh(RH rh) throws SQLException, IOException {
    this.rh = rh;

    loadRh();
  }

  private void loadRh() throws SQLException, IOException {
    nameLabel.setText(rh.getNome());

    List<Vaga> vagas = vagaService.listVagasByUsuario(rh.getIdUsuario());
    Usuario usuario = usuarioService.getUsuario(rh.getIdUsuario());
    List<CandidaturaRh> aprovados = vagaService.getApprovedCandidaciesByUsuario(usuario);
    List<CandidaturaRh> candidaturas = vagaService.listCandidaturasByRh(rh.getId());

    List<CandidaturaRh> recusados = new ArrayList<CandidaturaRh>();

    for(CandidaturaRh c:candidaturas) {
      if(c.getStatus() == StatusCandidatura.RECUSADO) {
        recusados.add(c);
      }
    } 

    for(int i = 1; i <= 4;) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skillItem.fxml"));
      Parent skillItem = loader.load();
      SkillsController skillController = loader.getController();

      if(i == 1) {
        if(vagas.size() == 1) {
          skillController.setSkill(vagas.size()+" vaga");   
          skillContainer.getChildren().add(skillItem);
        } else {
          skillController.setSkill(vagas.size()+" vagas");   
          skillContainer.getChildren().add(skillItem);
        }
        
      } else if(i == 2) {
        if(aprovados.size() == 1) {
          skillController.setSkill(aprovados.size()+" aprovado");   
          skillContainer.getChildren().add(skillItem);
        } else {
          skillController.setSkill(aprovados.size()+" aprovados");   
          skillContainer.getChildren().add(skillItem);
        }
      } else if(i == 3) {
        if(recusados.size() == 1) {
          skillController.setSkill(recusados.size()+" recusado");   
          skillContainer.getChildren().add(skillItem);
        } else {
          skillController.setSkill(recusados.size()+" recusados");   
          skillContainer.getChildren().add(skillItem);
        }
      } else if(i == 4) {
        skillController.setSkill(TipoUsuario.RH.toString());   
        skillContainer.getChildren().add(skillItem);
      }

      i++;
    }
  }
}