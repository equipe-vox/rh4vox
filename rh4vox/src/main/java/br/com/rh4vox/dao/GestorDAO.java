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
    Connection conn = getConnection(); 

    String sql = String.format("SELECT * FROM gestor WHERE id_usuario = %s", usuario.getId());
    
    Gestor gestor = null;

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();


      while(rs.next()) {
        gestor = new Gestor();

        gestor.setId(rs.getInt("id"));
        gestor.setNome(rs.getString("nome"));
        gestor.setCpf(rs.getString("cpf"));
        gestor.setIdUsuario(rs.getInt("id_usuario"));
        break;
      }

      stmt.close();
      rs.close();
      conn.close();

      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return gestor;
  }

  public void insertGestor(String nome, String cpf, Integer idUsuario) throws SQLException {
    
    executeQuery(String.format("INSERT INTO gestor (nome, cpf, id_usuario) VALUES('%s', '%s', %s)", nome, cpf, idUsuario));
  }

  public List<Gestor> listGestor() throws SQLException {
    Connection conn = getConnection(); 

    List<Gestor> gestores = new ArrayList<Gestor>();
    String sql = "SELECT * FROM gestor";
    
    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        Gestor gestor = new Gestor();

        gestor.setId(rs.getInt("id"));
        gestor.setNome(rs.getString("nome"));
        gestor.setCpf(rs.getString("cpf"));
        gestor.setIdUsuario(rs.getInt("id_usuario"));
        gestores.add(gestor);
      }

      stmt.close();
      rs.close();
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return gestores;
  }
}