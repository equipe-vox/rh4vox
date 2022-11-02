package br.com.rh4vox.service;

import java.sql.SQLException;

import br.com.rh4vox.dao.RHDAO;

public class RHService {
  private RHDAO dao = new RHDAO();

  public void insertRH(
    String nome,
    String cpf,
    Integer idUsuario
  ) throws SQLException {
    dao.insertRH(nome, cpf, idUsuario);
  }
}
