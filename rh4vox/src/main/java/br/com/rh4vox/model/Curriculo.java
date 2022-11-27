package br.com.rh4vox.model;

public class Curriculo {
  private Integer id;
  private String bio;
  private String objetivo;
  private String habilidades;
  private String formacao;
  private String experiencia;
  private Integer idCandidato;
  private String site;
  private String linkedin;
  private String git;


  public Curriculo(
    Integer id,
    String bio,
    String objetivo,
    String habilidades,
    String formacao,
    String experiencia,
    Integer idCandidato,
    String site,
    String linkedin,
    String git ) 
  {
    this.id = id;
    this.bio = bio;
    this.objetivo = objetivo;
    this.habilidades = habilidades;
    this.formacao = formacao;
    this.experiencia = experiencia;
    this.idCandidato = idCandidato;
    this.site = site;
    this.linkedin = linkedin;
    this.git = git;
  }

  public Curriculo() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Integer getIdCandidato() {
    return idCandidato;
  }

  public void setIdCandidato(Integer idCandidato) {
    this.idCandidato = idCandidato;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getLinkedin() {
    return linkedin;
  }

  public void setLinkedin(String linkedin) {
    this.linkedin = linkedin;
  }

  public String getGit() {
    return git;
  }

  public void setGit(String git) {
    this.git = git;
  }
}
