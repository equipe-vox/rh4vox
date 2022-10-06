package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.Usuario; 

public class CandidatoDAO extends BaseDAO {
  public Candidato getCandidato(int id) {
    return null;
  }

  public Candidato getCandidato(Usuario usuario) throws SQLException {
    Connection conn = getConnection(); 

    String sql = String.format("SELECT * FROM candidato WHERE id_usuario = %s", usuario.getId());
    
    Candidato c = null;

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();


      while(rs.next()) {
        c = new Candidato();

        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        c.setDataNasc(rs.getDate("data_nasc").toLocalDate());
        c.setCpf(rs.getString("cpf"));
        break;
      }

      stmt.close();
      rs.close();
      conn.close();

      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return c;
  }

  public void insertCandidato(Candidato candidato) throws SQLException {
    System.out.println("cadastro");

    executeQuery(String.format("INSERT INTO candidato (nome, data_nasc, cpf, id_usuario) VALUES('%s', '%s', '%s', '%s')", candidato.getNome(), candidato.getDataNasc(), candidato.getCpf(), candidato.getUsuario().getId()));
  }

  public void updateCandidato(Candidato candidato) {

  }

  public void removeCandidato(int id) {

  }

  public List<Candidato> listCandidatos() {
    return null;
  }
}