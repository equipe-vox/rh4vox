package br.com.rh4vox.model;

import java.util.Date;

public class Candidato {
  private Integer id;
  private String nome;
  private Long cpf;
  private Date dataNasc;

  public Candidato(
    Integer id,
    String nome,
    Long cpf,
    Date dataNasc
  ){
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.dataNasc = dataNasc;
  }

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

  public Long getCpf() {
    return cpf;
  }

  public void setCpf(Long cpf) {
    this.cpf = cpf;
  }

  public Date getDataNasc() {
    return dataNasc;
  }

  public void setDataNasc(Date dataNasc) {
    this.dataNasc = dataNasc;
  }
}
