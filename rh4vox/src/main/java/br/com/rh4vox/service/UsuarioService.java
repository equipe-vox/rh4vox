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
import br.com.rh4vox.exception.EmailAlreadyInUseException;
import br.com.rh4vox.exception.ValidationException;
import br.com.rh4vox.model.Candidato;
import br.com.rh4vox.model.CandidatoLogado;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.UsuarioLogado;
import br.com.rh4vox.validator.CpfValidator;
import br.com.rh4vox.validator.EmailValidator;
import br.com.rh4vox.validator.SenhaValidator;

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

  public Usuario cadastroCandidato(String email, String senha, String nome, LocalDate data_nasc, String cpf) throws SQLException, NoSuchAlgorithmException, ValidationException, EmailAlreadyInUseException {
    CpfValidator.validate(cpf);
    
    Usuario usuario = cadastroUsuario(email, senha, TipoUsuario.CANDIDATO);

    CandidatoService candidatoService = new CandidatoService();

    candidatoService.cadastro(nome, data_nasc, cpf, usuario);

    candidato = candDao.getCandidato(usuario);
    CandidatoLogado.getInstance().setCandidato(candidato);

    return usuario;
  }

  public Usuario cadastroRH(String email, String senha, String nome, TipoUsuario tipo, String cpf) throws SQLException, NoSuchAlgorithmException {
    Usuario usuario = cadastroUsuario(email, senha, tipo);

    RHService rhService = new RHService();

    rhService.insertRH(nome, cpf, usuario.getId());

    return usuario;
  }
  public Usuario cadastroAdm(String email, String senha, String nome, TipoUsuario tipo, String cpf) throws SQLException, NoSuchAlgorithmException {
    dao.insertUsuario(email, senhaHash(senha), tipo);

    Usuario usuario = login(email, senha);

    AdmService admService = new AdmService();

    admService.insertAdm(nome, cpf, usuario.getId());

    return usuario;
  }
  public Usuario cadastroGestor(String email, String senha, String nome, TipoUsuario tipo, String cpf) throws SQLException, NoSuchAlgorithmException {
    dao.insertUsuario(email, senhaHash(senha), tipo);

    Usuario usuario = login(email, senha);

    GestorService gestorService = new GestorService();

    gestorService.insertGestor(nome, cpf, usuario.getId());

    return usuario;
  }

  public void logoff() {
    UsuarioLogado.getInstance().logoff();
  }

  public void updateUsuario(String email) throws SQLException {
    dao.updateUsuario(email, UsuarioLogado.getInstance().getUsuario().getId());
  }

  private Usuario cadastroUsuario(String email, String senha, TipoUsuario tipo) throws SQLException, NoSuchAlgorithmException{
    EmailValidator.validate(email);

    if(emailAlreadyInUse(email)){
      throw new EmailAlreadyInUseException();
    }

    SenhaValidator.validate(senha);

    dao.insertUsuario(email, senhaHash(senha), TipoUsuario.CANDIDATO);
    return login(email, senha);
  }

  private String senhaHash(String senha) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");

    BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));

    return hash.toString(16);
  }

  private boolean emailAlreadyInUse(String email) throws SQLException {
    List<Usuario> usuarios = dao.listUsuarios();

    for(Usuario usuario:usuarios) {
      if(email.equals(usuario.getEmail())) {
        return true;
      }
    }

    return false;
  }
}
