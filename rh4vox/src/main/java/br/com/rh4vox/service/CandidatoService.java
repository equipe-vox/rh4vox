package br.com.rh4vox.service;

import java.sql.SQLException;
import java.time.LocalDate;

import br.com.rh4vox.dao.CandidatoDAO;
import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.Usuario;

public class CandidatoService {

  CandidatoDAO dao = new CandidatoDAO();
  
  public void cadastro(String nome, LocalDate data_nasc, String cpf, Usuario usuario) throws SQLException {
    Candidato candidato = new Candidato(null, nome, cpf, data_nasc, usuario);

    dao.insertCandidato(candidato);

  }

  public Candidato getCandidatoByUsuario(Usuario usuario) throws SQLException {
    return dao.getCandidato(usuario);

  }


}
