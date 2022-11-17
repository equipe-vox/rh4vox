package br.com.rh4vox.service;

import java.sql.SQLException;

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
}
