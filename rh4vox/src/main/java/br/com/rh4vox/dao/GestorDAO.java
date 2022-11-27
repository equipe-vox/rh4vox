package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rh4vox.model.Gestor;
import br.com.rh4vox.model.Usuario;

public class GestorDAO extends BaseDAO {
  public Gestor getGestor(Usuario usuario) throws SQLException {
    Gestor gestor = null;

    try {
      String query = "SELECT * FROM gestor WHERE id_usuario = ?";

      PreparedStatement stmt = getPreparedStatement(query);
      stmt.setInt(1, usuario.getId());

      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        gestor = new Gestor();

        gestor.setId(rs.getInt("id"));
        gestor.setNome(rs.getString("nome"));
        gestor.setCpf(rs.getString("cpf"));
        gestor.setIdUsuario(rs.getInt("id_usuario"));
        break;
      }

      rs.close();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return gestor;
  }

  public void insertGestor(String nome, String cpf, Integer idUsuario) throws SQLException {
    
    executeQuery(String.format("INSERT INTO gestor (nome, cpf, id_usuario) VALUES('%s', '%s', %s)", nome, cpf, idUsuario));
  }

  public List<Gestor> listGestor() throws SQLException {
    List<Gestor> gestores = new ArrayList<Gestor>();
    
    try {
      String query = "SELECT * FROM gestor";
      PreparedStatement stmt = getPreparedStatement(query);

      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        Gestor gestor = new Gestor();

        gestor.setId(rs.getInt("id"));
        gestor.setNome(rs.getString("nome"));
        gestor.setCpf(rs.getString("cpf"));
        gestor.setIdUsuario(rs.getInt("id_usuario"));
        gestores.add(gestor);
      }

      rs.close();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return gestores;
  }
}