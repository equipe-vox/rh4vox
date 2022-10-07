package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.rh4vox.model.Curriculo; 

public class CurriculoDAO extends BaseDAO {
  

  public Curriculo getCurriculo(int idCandidato) {
    return null;
  }

  public void insertCurrilo(Curriculo curriculo, Integer idCandidato) throws SQLException {
    executeQuery(String.format("INSERT INTO curriculo (bio, objetivo, habilidades, formacao, experiencia, id_candidato) VALUES('%s', '%s', '%s', '%s', '%s', %s)", curriculo.getBio(), curriculo.getObjetivo(), curriculo.getHabilidades(), curriculo.getFormacao(), curriculo.getExperiencia(), idCandidato));
  }

  public void updateCurriculo(Curriculo curriculo) {

  }

  public void deleteCurriculo(int id) {

  }

  public Curriculo getIdCurriculo(Integer idCandidato) throws SQLException {
    Curriculo c = null;

    Connection conn = getConnection(); 

    String sql = String.format("SELECT * FROM curriculo where id_candidato = %s", idCandidato);


    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while(rs.next()) {

        
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

  public List<Curriculo> listCurriculos() {
    return null;
  }
}
