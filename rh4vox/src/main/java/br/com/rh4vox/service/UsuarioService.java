package br.com.rh4vox.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import br.com.rh4vox.dao.CandidatoDAO;
import br.com.rh4vox.dao.UsuarioDAO;
import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.UsuarioLogado;

public class UsuarioService {

  UsuarioDAO dao = new UsuarioDAO();
  CandidatoDAO candDao = new CandidatoDAO();
  Candidato candidato;

  public Usuario login(String email, String senha) throws SQLException {
    candidato = new Candidato();


    List<Usuario> usuarios = dao.listUsuarios();

    for(Usuario usuario:usuarios) {
      if(email.equals(usuario.getEmail()) && senha.equals(usuario.getSenha())) {
        UsuarioLogado.getInstance().setUsuario(usuario);
        candidato = candDao.getCandidato(usuario);
        
        CandidatoLogado.getInstance().setCandidato(candidato);
        return usuario;
      }
    }

    return null;
  }

  public Usuario cadastroCandidato(String email, String senha, String nome, LocalDate data_nasc, String cpf) throws SQLException {
    dao.insertUsuario(email, senha, TipoUsuario.CANDIDATO);

    Usuario usuario = login(email, senha);

    CandidatoService candidatoService = new CandidatoService();

    candidatoService.cadastro(nome, data_nasc, cpf, usuario);

    candidato = candDao.getCandidato(usuario);
    CandidatoLogado.getInstance().setCandidato(candidato);

    return usuario;
  }

  public Usuario emailAlreadyInUse(String email) throws SQLException {
    List<Usuario> usuarios = dao.listUsuarios();

    for(Usuario usuario:usuarios) {
      if(email.equals(usuario.getEmail())) {
        return usuario;
      }
    }
    return null;
  }

  public void logoff() {
    UsuarioLogado.getInstance().logoff();
  }

}
