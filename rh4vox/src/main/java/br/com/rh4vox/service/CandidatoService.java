package br.com.rh4vox.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.com.rh4vox.dao.CandidatoDAO;
import br.com.rh4vox.dao.CurriculoDAO;
import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Usuario;

public class CandidatoService {

  CandidatoDAO dao = new CandidatoDAO();
  CurriculoDAO curriculoDAO = new CurriculoDAO();
  
  public void cadastro(String nome, LocalDate data_nasc, String cpf, Usuario usuario) throws SQLException {
    Candidato candidato = new Candidato(null, nome, cpf, data_nasc, usuario, null);

    dao.insertCandidato(candidato);

  }

  public Candidato getCandidatoByUsuario(Usuario usuario) throws SQLException {
    Candidato candidato = new Candidato();

    candidato = dao.getCandidato(usuario);
    CandidatoLogado.getInstance().setCandidato(candidato);
    
    return candidato;

  }

  public Candidato cpfAlreadyExists(String cpf) throws SQLException {
    List<Candidato> candidatos = dao.listCandidatos();

    for(Candidato candidato:candidatos) {
      if(cpf.equals(candidato.getCpf())) {
        return candidato;
      }
    }
    return null;
  }

  public void updateCandidato(String nome, String telefone, Integer id) throws SQLException {
    dao.updateCandidato(nome, telefone, id);
  }
}
