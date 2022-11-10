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
  
  public boolean checkCpf(String cpf){
    String cpfNumbers = cpf.replace(".", "").replace("-", "");
    int primeiroDigito = Character.getNumericValue(cpfNumbers.charAt(9));
    int segundoDigito = Character.getNumericValue(cpfNumbers.charAt(10));
    int somaPrimeiroDigito = 0, somaSegundoDigito = 0, sobra;
    
    for(int i=0; i<9; i++){
      int numberAtPosition = Character.getNumericValue(cpfNumbers.charAt(i));
      somaPrimeiroDigito += numberAtPosition * (10-i);
    }

    sobra = 11 - (somaPrimeiroDigito % 11);

    if(sobra > 10 && primeiroDigito != 0 || sobra < 10 && sobra != primeiroDigito){
      return false;
    }

    for(int i=0; i<10; i++){
      int numberAtPosition = Character.getNumericValue(cpfNumbers.charAt(i));
      somaSegundoDigito += numberAtPosition * (11-i);
    }

    sobra = 11 - (somaSegundoDigito % 11);

    if(sobra > 10 && segundoDigito != 0 || sobra < 10 && sobra != segundoDigito){
      return false;
    }

    return true;
  }
}
