package br.com.rh4vox.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.Usuario; 

public class UsuarioDAO extends BaseDAO {

  public Usuario getUsuario(Integer id) throws SQLException {
    Usuario usuario = null;

    try {
      String query = "SELECT * FROM usuario WHERE id = ?";

      PreparedStatement stmt = getPreparedStatement(query);
      stmt.setInt(1, id);

      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        usuario = new Usuario();

        usuario.setId(rs.getInt("id"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTipo(TipoUsuario.valueOf(rs.getString("tipo")));
        break;
      }

      stmt.close();
      rs.close();      
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return usuario;
  }

  public void insertUsuario(String email, String senha, TipoUsuario tipo) throws SQLException {
    
    executeQuery(String.format("INSERT INTO usuario (email, senha, tipo) VALUES('%s', '%s', '%s')", email, senha, tipo.toString()));
  }

  public void updateUsuario(String email, Integer id) throws SQLException {
    String query = "UPDATE usuario SET email = ? WHERE id = ?";

    PreparedStatement statement = getPreparedStatement(query);
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
    List<Usuario> usuarios = new ArrayList<>();
    
    try {
      String query = "SELECT * FROM usuario";

      PreparedStatement stmt = getPreparedStatement(query);

      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        Usuario u = new Usuario();

        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));
        u.setTipo(TipoUsuario.valueOf(rs.getString("tipo")));
        
        usuarios.add(u);
      }

      stmt.close();
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return usuarios;
  }
}