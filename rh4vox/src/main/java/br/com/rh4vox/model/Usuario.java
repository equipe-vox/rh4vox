package br.com.rh4vox.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.rh4vox.enums.TipoUsuario;

public class Usuario {
  private Integer id;
  private String email;
  private String senha;
  private TipoUsuario tipo;

  public Usuario(
  ) {
    
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public TipoUsuario getTipo() {
    return tipo;
  }

  public void setTipo(TipoUsuario tipo) {
    this.tipo = tipo;
  }
}
