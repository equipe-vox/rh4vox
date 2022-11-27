package br.com.rh4vox.service;

import java.sql.SQLException;
import java.util.List;

import br.com.rh4vox.dao.RHDAO;
import br.com.rh4vox.model.RH;
import br.com.rh4vox.model.Usuario;

public class RHService {
  private RHDAO dao = new RHDAO();

  public void insertRH(
    String nome,
    String cpf,
    Integer idUsuario
  ) throws SQLException {
    dao.insertRH(nome, cpf, idUsuario);
  }

  public RH getRhByUsuario(Usuario usuario) throws SQLException {
    return dao.getRH(usuario);
  }

  public List<RH> getRhs() throws SQLException {
    return dao.listRH();
  }

  public Integer getVagasByUsuario(Integer integer) {
    return null;
  }

  public Integer getCandidaciesByUsuario(Usuario usuario) {
    return null;
  }

  public Integer getApprovedCandidaciesByUsuario(Usuario usuario) {
    return null;
  }


}
