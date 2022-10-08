package br.com.rh4vox.service;

import java.sql.SQLException;

import br.com.rh4vox.dao.CurriculoDAO;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Curriculo;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.UsuarioLogado;

public class CurriculoService {
  private CurriculoDAO dao = new CurriculoDAO();
  private CandidatoService candidatoService = new CandidatoService();

  public void saveCurriculo(
    String bio,
    String objetivo,
    String habilidades,
    String formacao,
    String experiencia,
    String site,
    String linkedin,
    String git
  ) throws SQLException {
    Curriculo curriculo = new Curriculo(
      null,
      bio,
      objetivo,
      habilidades,
      formacao,
      experiencia,
      null,
      site,
      linkedin,
      git
    );

    Usuario usuario = UsuarioLogado.getInstance().getUsuario();
    Integer idCandidato = candidatoService.getCandidatoByUsuario(usuario).getId();

    dao.insertCurrilo(curriculo, idCandidato);
  }

  public Curriculo getCurriculo() throws SQLException {
    Curriculo c = null;

    Integer idCandidato = CandidatoLogado.getInstance().getCandidato().getId();

    c = dao.getCurriculo(idCandidato);

    if(c == null) {
      return null;
    } else {
      return c;
    }


  }
}
