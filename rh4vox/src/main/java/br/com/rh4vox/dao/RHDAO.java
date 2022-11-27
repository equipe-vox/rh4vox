package br.com.rh4vox.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rh4vox.model.RH;
import br.com.rh4vox.model.Usuario;

public class RHDAO extends BaseDAO {

  public RH getRH(Integer id) throws SQLException {
    return null;
  }

  public RH getRH(Usuario usuario) throws SQLException {
    RH rh = null;

    try {
      String query = "SELECT * FROM rh WHERE id_usuario = ?";

      PreparedStatement stmt = getPreparedStatement(query);
      stmt.setInt(1, usuario.getId());

      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        rh = new RH();

        rh.setId(rs.getInt("id"));
        rh.setNome(rs.getString("nome"));
        rh.setCpf(rs.getString("cpf"));
        rh.setIdUsuario(rs.getInt("id_usuario"));
        break;
      }

      rs.close();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rh;
  }

  public void insertRH(String nome, String cpf, Integer idUsuario) throws SQLException {
    
    executeQuery(String.format("INSERT INTO rh (nome, cpf, id_usuario) VALUES('%s', '%s', %s)", nome, cpf, idUsuario));
  }

  public void updateRH(Usuario usuario) {

  }

  public void removeRH(int id) {

  }

  public List<RH> listRH() throws SQLException {
    List<RH> rhs = new ArrayList<>();

    try {
      String query = "SELECT * FROM rh";

      PreparedStatement stmt = getPreparedStatement(query);

      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        RH rh = new RH();

        rh.setId(rs.getInt("id"));
        rh.setNome(rs.getString("nome"));
        rh.setCpf(rs.getString("cpf"));
        rh.setIdUsuario(rs.getInt("id_usuario"));
        rhs.add(rh);
      }

      stmt.close();
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return rhs;
  }
}
