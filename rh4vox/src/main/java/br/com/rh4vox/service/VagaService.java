package br.com.rh4vox.service;

import java.math.BigDecimal;
import java.sql.SQLException;

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
    String cargo
  ) throws SQLException {
    Vaga vaga = new Vaga(
      null,
      nome,
      descricao,
      salario,
      regime,
      negociavel,
      aberto,
      cargo
    );
    
    dao.insertVaga(vaga);
  }
}
