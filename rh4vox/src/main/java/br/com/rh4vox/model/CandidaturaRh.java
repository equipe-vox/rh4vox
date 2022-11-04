package br.com.rh4vox.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.rh4vox.enums.Regime;
import br.com.rh4vox.enums.StatusCandidatura;

public class CandidaturaRh {
	private Integer idCandidato;
  private String nomeCand;
  private String habilidades;
  private String objetivo;
  private String formacao;
  private String experiencia;
	private String telefone;
  private java.time.LocalDate dataNasc;

	private Integer idVaga;
  private String nomeVaga;
  private String descricao;
  private String cargo;
  private BigDecimal salario;
  private Regime regime;
  private Boolean negociavel;
	private StatusCandidatura status;


  public CandidaturaRh() {}

  public CandidaturaRh(
		Integer idCandidato,
	  Integer idVaga,
    String nomeCand,
    String habilidades,
    String objetivo,
    String formacao,
    String experiencia,
		String telefone,
    LocalDate dataNasc,
    String nomeVaga,
    String descricao,
    String cargo,
    BigDecimal salario,
    Regime regime,
    Boolean negociavel,
		StatusCandidatura status
  ) {
		this.idCandidato = idCandidato;
		this.idVaga = idVaga;
    this.nomeCand = nomeCand;
    this.habilidades = habilidades;
    this.objetivo = objetivo;
    this.formacao = formacao;
    this.experiencia = experiencia;
		this.telefone = telefone;
    this.dataNasc = dataNasc;
    this.nomeVaga = nomeVaga;
    this.descricao = descricao;
    this.cargo = cargo;
    this.salario = salario;
    this.regime = regime;
    this.negociavel = negociavel;
		this.status = status;
  }

	public Integer getIdCandidato() {
		return this.idCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}

	public Integer getIdVaga() {
		return this.idVaga;
	}

	public void setIdVaga(Integer idVaga) {
		this.idVaga = idVaga;
	}

	public String getNomeCand() {
		return this.nomeCand;
	}

	public void setNomeCand(String nomeCand) {
		this.nomeCand = nomeCand;
	}

	public String getHabilidades() {
		return this.habilidades;
	}

	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getFormacao() {
		return this.formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getExperiencia() {
		return this.experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNasc() {
		return this.dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getNomeVaga() {
		return this.nomeVaga;
	}

	public void setNomeVaga(String nomeVaga) {
		this.nomeVaga = nomeVaga;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public BigDecimal getSalario() {
		return this.salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Regime getRegime() {
		return this.regime;
	}

	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	public Boolean getNegociavel() {
		return this.negociavel;
	}

	public void setNegociavel(Boolean negociavel) {
		this.negociavel = negociavel;
	}

	public StatusCandidatura getStatus() {
		return this.status;
	}

	public void setStatus(StatusCandidatura status) {
		this.status = status;
	}

}
