package br.com.rh4vox.dao;

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
    Candidato c = null;

    try {
      String query = "SELECT * FROM candidato WHERE id_usuario = ?";

      PreparedStatement stmt = getPreparedStatement(query);
      stmt.setInt(1, usuario.getId());

      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        c = new Candidato();

        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome_candidato"));
        c.setDataNasc(rs.getDate("data_nasc").toLocalDate());
        c.setCpf(rs.getString("cpf"));
        c.setTelefone(rs.getString("telefone"));
        break;
      }

      stmt.close();
      rs.close();      
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return c;
  }

  public void insertCandidato(Candidato candidato) throws SQLException {
    executeQuery(String.format("INSERT INTO candidato (nome_candidato, data_nasc, cpf, id_usuario) VALUES('%s', '%s', '%s', '%s')", candidato.getNome(), candidato.getDataNasc(), candidato.getCpf(), candidato.getUsuario().getId()));
  }

  public void updateCandidato(String nome, String telefone, Integer id) throws SQLException {
    String query = "UPDATE candidato SET nome_candidato=?, telefone=? WHERE id=?";

    PreparedStatement statement = getPreparedStatement(query);
    statement.setString(1, nome);
    statement.setString(2, telefone);
    statement.setInt(3, id);
    
    int rowsUpdated = statement.executeUpdate();

    if (rowsUpdated > 0) {
      System.out.println("An existing candidato was updated successfully!");
    }
  }

  public void removeCandidato(int id) {

  }

  public List<Candidato> listCandidatos() throws SQLException {
    List<Candidato> candidatos = new ArrayList<>();
    String query = "SELECT * FROM candidato";
    
    try {
      PreparedStatement stmt = getPreparedStatement(query);
      ResultSet rs = stmt.executeQuery();

      while(rs.next()) {
        Candidato c = new Candidato();

        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome_candidato"));
        c.setDataNasc(rs.getDate("data_nasc").toLocalDate());
        c.setCpf(rs.getString("cpf"));
        candidatos.add(c);
        break;
      }

      rs.close();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return candidatos;
  }
}