package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.rh4vox.model.Curriculo; 

public class CurriculoDAO extends BaseDAO {
  

  public Curriculo getCurriculo(int idCandidato) throws SQLException {
    Connection conn = getConnection(); 

    String sql = String.format("SELECT * FROM curriculo WHERE id_candidato = %s", idCandidato);
    
    Curriculo c = null;

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();


      while(rs.next()) {
        c = new Curriculo();

        c.setId(rs.getInt("id"));
        c.setBio(rs.getString("bio"));
        c.setObjetivo(rs.getString("objetivo"));
        c.setHabilidades(rs.getString("habilidades"));
        c.setFormacao(rs.getString("formacao"));
        c.setExperiencia(rs.getString("experiencia"));
        c.setIdCandidato(rs.getInt("id_candidato"));
        c.setSite(rs.getString("site"));
        c.setLinkedin(rs.getString("linkedin"));
        c.setGit(rs.getString("git"));
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

  public void insertCurrilo(Curriculo curriculo, Integer idCandidato) throws SQLException {
    Connection conn = getConnection(); 

    String sql = String.format("SELECT * FROM curriculo WHERE id_candidato = %s", idCandidato);
    
    Curriculo c = null;

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();


      while(rs.next()) {
        c = new Curriculo();

        c.setId(rs.getInt("id"));
        break;
      }

      stmt.close();
      rs.close();
      conn.close();     
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if (c != null) {
      String updateSql = String.format("UPDATE curriculo SET bio = '%s', objetivo = '%s', habilidades = '%s',formacao = '%s',experiencia = '%s', site = '%s', linkedin = '%s', git = '%s' WHERE id_candidato = %s",
        curriculo.getBio(),
        curriculo.getObjetivo(),
        curriculo.getHabilidades(),
        curriculo.getFormacao(),
        curriculo.getExperiencia(),
        curriculo.getSite(),
        curriculo.getLinkedin(),
        curriculo.getGit(),
        idCandidato
      );

      executeQuery(updateSql);
    } else {
      executeQuery(String.format("INSERT INTO curriculo (bio, objetivo, habilidades, formacao, experiencia, id_candidato, site, linkedin, git) SELECT '%s', '%s', '%s', '%s', '%s', %s, '%s', '%s', '%s' WHERE NOT EXISTS (SELECT id_candidato FROM curriculo where id_candidato = %s)", curriculo.getBio(), curriculo.getObjetivo(), curriculo.getHabilidades(), curriculo.getFormacao(), curriculo.getExperiencia(), idCandidato, curriculo.getSite(), curriculo.getLinkedin(), curriculo.getGit(), idCandidato));
    }   
  }

  public void updateCurriculo(Curriculo curriculo) {

  }

  public void deleteCurriculo(int id) {

  }

  public List<Curriculo> listCurriculos() {
    return null;
  }
}
