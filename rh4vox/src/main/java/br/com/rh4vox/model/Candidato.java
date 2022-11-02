package br.com.rh4vox.model;

import java.time.LocalDate;

public class Candidato {
  private Integer id;
  private String nome;
  private String cpf;
  private LocalDate dataNasc;
  private Usuario usuario;

  public Candidato(
    Integer id,
    String nome,
    String cpf,
    LocalDate dataNasc,
    Usuario usuario
  ){
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.dataNasc = dataNasc;
    this.usuario = usuario;
  }

  public Candidato() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public LocalDate getDataNasc() {
    return dataNasc;
  }

  public void setDataNasc(LocalDate dataNasc) {
    this.dataNasc = dataNasc;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  
}
