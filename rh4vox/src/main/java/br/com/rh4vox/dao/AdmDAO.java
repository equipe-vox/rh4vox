package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rh4vox.model.Administrador;
import br.com.rh4vox.model.Usuario; 

public class AdmDAO extends BaseDAO {

  public Administrador getAdm(Integer id) throws SQLException {
    return null;
  }

  public Administrador getAdm(Usuario usuario) throws SQLException {
    Connection conn = getConnection(); 

    String sql = String.format("SELECT * FROM adm WHERE id_usuario = %s", usuario.getId());
    
    Administrador adm = null;

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();


      while(rs.next()) {
        adm = new Administrador();

        adm.setId(rs.getInt("id"));
        adm.setNome(rs.getString("nome"));
        adm.setCpf(rs.getString("cpf"));
        adm.setIdUsuario(rs.getInt("id_usuario"));
        break;
      }

      stmt.close();
      rs.close();
      conn.close();

      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return adm;
  }

  public void insertAdm(String nome, String cpf, Integer idUsuario) throws SQLException {
    
    executeQuery(String.format("INSERT INTO administrador (nome, cpf, id_usuario) VALUES('%s', '%s', %s)", nome, cpf, idUsuario));
  }

  public void updateAdm(Usuario usuario) {

  }

  public void removeAdm(int id) {

  }

  public List<Administrador> listAdm() throws SQLException {
    Connection conn = getConnection(); 

    List<Administrador> adms = new ArrayList<>();
    String sql = "SELECT * FROM adm";
    
    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        Administrador adm = new Administrador();

        adm.setId(rs.getInt("id"));
        adm.setNome(rs.getString("nome"));
        adm.setCpf(rs.getString("cpf"));
        adm.setIdUsuario(rs.getInt("id_usuario"));
        adms.add(adm);
      }

      stmt.close();
      rs.close();
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return adms;
  }
}