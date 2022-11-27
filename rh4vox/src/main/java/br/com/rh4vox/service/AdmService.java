package br.com.rh4vox.service;

import java.sql.SQLException;

import br.com.rh4vox.dao.AdmDAO;
import br.com.rh4vox.model.Administrador;
import br.com.rh4vox.model.Usuario;

public class AdmService {
  private AdmDAO dao = new AdmDAO();

  public void insertAdm(
    String nome,
    String cpf,
    Integer idUsuario
  ) throws SQLException {
    dao.insertAdm(nome, cpf, idUsuario);
  }

  public Administrador getAdmByUsuario(Usuario usuario) throws SQLException {
    return dao.getAdm(usuario);
  }

}
