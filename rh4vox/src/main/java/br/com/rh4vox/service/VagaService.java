package br.com.rh4vox.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import br.com.rh4vox.dao.VagaDAO;
import br.com.rh4vox.enums.Regime;
import br.com.rh4vox.model.*;

public class VagaService {
  VagaDAO dao = new VagaDAO();

  public void saveJob(Vaga vaga) {
		BaseVagas.getInstance().addVaga(vaga);

		new VagaExtractor("vagas.csv").extractVagas(BaseVagas.getInstance().getVagas());
  }

  public void cadastroVaga(
    String nome, 
    String descricao, 
    BigDecimal salario, 
    Regime regime, 
    Boolean negociavel, 
    Boolean aberto, 
    String cargo,
    Integer idUsuario
  ) throws SQLException {
    Vaga vaga = new Vaga(
      null,
      nome,
      descricao,
      salario,
      regime,
      negociavel,
      aberto,
      cargo,
      idUsuario
    );
    
    dao.insertVaga(vaga);
  }

  public void updateVaga(
    Integer id,
    String nome, 
    String descricao, 
    BigDecimal salario, 
    Regime regime, 
    Boolean negociavel, 
    String cargo
  ) throws SQLException {
    Vaga vaga = new Vaga(
      id,
      nome,
      descricao,
      salario,
      regime,
      negociavel,
      true,
      cargo,
      null
    );
    
    dao.updateVaga(vaga);
  }

  public void removeVaga(Integer id) throws SQLException  {
    dao.removeVaga(id);
  }

  public void createCandidatura(Integer idVaga, Integer idCandidato) throws SQLException {
    dao.insertCandidatura(idVaga, idCandidato);
  }

  public List<Candidatura> listVagas(Integer idVaga, Integer idCandidato) throws SQLException {
    return dao.listCandidaturas(idVaga, idCandidato);
  } 
}
