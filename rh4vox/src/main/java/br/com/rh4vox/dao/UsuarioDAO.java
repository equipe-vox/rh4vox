package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.Usuario; 

public class UsuarioDAO extends BaseDAO {

  public Usuario getUsuario(int id) throws SQLException {
    return null;
  }

  public void insertUsuario(String email, String senha, TipoUsuario tipo) throws SQLException {
    
    executeQuery(String.format("INSERT INTO usuario (email, senha, tipo) VALUES('%s', '%s', '%s')", email, senha, tipo.toString()));
  }

  public void updateUsuario(String email, Integer id) throws SQLException {
    Connection conn = getConnection(); 
    String sql = "UPDATE usuario SET email=? WHERE id=?";

    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setString(1, email);
    statement.setInt(2, id);
    
    int rowsUpdated = statement.executeUpdate();
    if (rowsUpdated > 0) {
      System.out.println("An existing usuario was updated successfully!");
    }
  }

  public void removeUsuario(int id) {

  }

  public List<Usuario> listUsuarios() throws SQLException {
    Connection conn = getConnection(); 

    List<Usuario> usuarios = new ArrayList<>();
    String sql = "SELECT * FROM usuario";
    
    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        Usuario u = new Usuario();

        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));
        u.setTipo(TipoUsuario.valueOf((rs.getString("tipo"))));
        usuarios.add(u);
      }

      stmt.close();
      rs.close();
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return usuarios;
  }
}