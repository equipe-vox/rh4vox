package br.com.rh4vox.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.com.rh4vox.dao.CandidatoDAO;
import br.com.rh4vox.dao.RHDAO;
import br.com.rh4vox.dao.UsuarioDAO;
import br.com.rh4vox.enums.TipoUsuario;
import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.UsuarioLogado;

public class UsuarioService {

  UsuarioDAO dao = new UsuarioDAO();
  CandidatoDAO candDao = new CandidatoDAO();
  RHDAO rhDao = new RHDAO();
  Candidato candidato;

  public Usuario login(String email, String senha) throws SQLException, NoSuchAlgorithmException {
    candidato = new Candidato();

    List<Usuario> usuarios = dao.listUsuarios();

    for(Usuario usuario:usuarios) {
      if(email.equals(usuario.getEmail()) && senhaHash(senha).equals(usuario.getSenha())) {
        UsuarioLogado.getInstance().setUsuario(usuario);
        candidato = candDao.getCandidato(usuario);
        
        CandidatoLogado.getInstance().setCandidato(candidato);
        return usuario;
      }
    }

    return null;
  }

  public Usuario cadastroCandidato(String email, String senha, String nome, LocalDate data_nasc, String cpf) throws SQLException, NoSuchAlgorithmException {
    dao.insertUsuario(email, senhaHash(senha), TipoUsuario.CANDIDATO);

    Usuario usuario = login(email, senha);

    CandidatoService candidatoService = new CandidatoService();

    candidatoService.cadastro(nome, data_nasc, cpf, usuario);

    candidato = candDao.getCandidato(usuario);
    CandidatoLogado.getInstance().setCandidato(candidato);

    return usuario;
  }

  public Usuario cadastroRH(String email, String senha, String nome, TipoUsuario tipo, String cpf) throws SQLException, NoSuchAlgorithmException {
    dao.insertUsuario(email, senhaHash(senha), tipo);

    Usuario usuario = login(email, senha);

    RHService rhService = new RHService();

    rhService.insertRH(nome, cpf, usuario.getId());

    return usuario;
  }

  public String senhaHash(String senha) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");

    BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));

    return hash.toString(16);
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
