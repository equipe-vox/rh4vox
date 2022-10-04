package br.com.rh4vox.model;

public class Curriculo {
  private Integer idCandidato;
  private String bio;
  private String objetivo;
  private String habilidades;
  private String formacao;
  private String experiencia;

  public Curriculo(
    Integer idCandidato,
    String bio,
    String objetivo,
    String habilidades,
    String formacao,
    String experiencia
  ) {
    this.idCandidato = idCandidato;
    this.bio = bio;
    this.objetivo = objetivo;
    this.habilidades = habilidades;
    this.formacao = formacao;
    this.experiencia = experiencia;
  }

  public Integer getIdCandidato() {
    return idCandidato;
  }

  public void setIdCandidato(Integer idCandidato) {
    this.idCandidato = idCandidato;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getObjetivo() {
    return objetivo;
  }

  public void setObjetivo(String objetivo) {
    this.objetivo = objetivo;
  }

  public String getHabilidades() {
    return habilidades;
  }

  public void setHabilidades(String habilidades) {
    this.habilidades = habilidades;
  }

  public String getFormacao() {
    return formacao;
  }

  public void setFormacao(String formacao) {
    this.formacao = formacao;
  }

  public String getExperiencia() {
    return experiencia;
  }

  public void setExperiencia(String experiencia) {
    this.experiencia = experiencia;
  }

}
