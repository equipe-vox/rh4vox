package br.com.rh4vox.service;

import java.sql.SQLException;
import java.time.LocalDate;

import br.com.rh4vox.dao.CandidatoDAO;
import br.com.rh4vox.dao.CurriculoDAO;
import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Usuario;

public class CandidatoService {

  CandidatoDAO dao = new CandidatoDAO();
  CurriculoDAO curriculoDAO = new CurriculoDAO();
  
  public void cadastro(String nome, LocalDate data_nasc, String cpf, Usuario usuario) throws SQLException {
    Candidato candidato = new Candidato(null, nome, cpf, data_nasc, usuario);

    dao.insertCandidato(candidato);

  }

  public Candidato getCandidatoByUsuario(Usuario usuario) throws SQLException {
    Candidato candidato = new Candidato();

    candidato = dao.getCandidato(usuario);
    CandidatoLogado.getInstance().setCandidato(candidato);
    
    return candidato;

  }

}
