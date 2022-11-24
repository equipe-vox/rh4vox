package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rh4vox.model.RH;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.Vaga; 

public class RHDAO extends BaseDAO {

  public RH getRH(Integer id) throws SQLException {
    return null;
  }

  public RH getRH(Usuario usuario) throws SQLException {
    Connection conn = getConnection(); 

    String sql = String.format("SELECT * FROM rh WHERE id_usuario = %s", usuario.getId());
    
    RH rh = null;

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();


      while(rs.next()) {
        rh = new RH();

        rh.setId(rs.getInt("id"));
        rh.setNome(rs.getString("nome"));
        rh.setCpf(rs.getString("cpf"));
        rh.setIdUsuario(rs.getInt("id_usuario"));
        break;
      }

      stmt.close();
      rs.close();
      conn.close();

      
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
    Connection conn = getConnection(); 

    List<RH> rhs = new ArrayList<>();
    String sql = "SELECT * FROM rh";
    
    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
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
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return rhs;
  }
}