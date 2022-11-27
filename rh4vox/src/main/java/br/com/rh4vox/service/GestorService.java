package br.com.rh4vox.service;

import java.sql.SQLException;

import br.com.rh4vox.dao.GestorDAO;
import br.com.rh4vox.model.Gestor;
import br.com.rh4vox.model.Usuario;

public class GestorService {
  private GestorDAO dao = new GestorDAO();

  public void insertGestor(
    String nome,
    String cpf,
    Integer idUsuario
  ) throws SQLException {
    dao.insertGestor(nome, cpf, idUsuario);
  }

  public Gestor getGestorByUsuario(Usuario usuario) throws SQLException {
    return dao.getGestor(usuario);
  }
}
