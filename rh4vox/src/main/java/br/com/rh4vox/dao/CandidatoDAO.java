package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    executeQuery(String.format("INSERT INTO candidato (nome, data_nasc, cpf, id_usuario) VALUES('%s', '%s', '%s', '%s')", candidato.getNome(), candidato.getDataNasc(), candidato.getCpf(), candidato.getUsuario().getId()));
  }

  public void updateCandidato(Candidato candidato) {

  }

  public void removeCandidato(int id) {

  }

  public List<Candidato> listCandidatos() throws SQLException {
    Connection conn = getConnection(); 

    List<Candidato> candidatos = new ArrayList<>();
    String sql = "SELECT * FROM candidato";
    
    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        Candidato c = new Candidato();

        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        c.setDataNasc(rs.getDate("data_nasc").toLocalDate());
        c.setCpf(rs.getString("cpf"));
        candidatos.add(c);
        break;
      }

      stmt.close();
      rs.close();
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return candidatos;
  }
}