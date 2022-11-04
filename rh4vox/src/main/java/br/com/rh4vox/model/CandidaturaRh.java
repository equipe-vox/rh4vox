package br.com.rh4vox.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.rh4vox.enums.Regime;

public class CandidaturaRh {
  private String nomeCand;
  private String habilidades;
  private String objetivo;
  private String formacao;
  private String experiencia;
  private java.time.LocalDate dataNasc;

  private String nomeVaga;
  private String descricao;
  private String cargo;
  private BigDecimal salario;
  private Regime regime;
  private Boolean negociavel;

  public CandidaturaRh() {}

  public CandidaturaRh(
    String nomeCand,
    String habilidades,
    String objetivo,
    String formacao,
    String experiencia,
    LocalDate dataNasc,
    String nomeVaga,
    String descricao,
    String cargo,
    BigDecimal salario,
    Regime regime,
    Boolean negociavel
  ) {
    this.nomeCand = nomeCand;
    this.habilidades = habilidades;
    this.objetivo = objetivo;
    this.formacao = formacao;
    this.experiencia = experiencia;
    this.dataNasc = dataNasc;
    this.nomeVaga = nomeVaga;
    this.descricao = descricao;
    this.cargo = cargo;
    this.salario = salario;
    this.regime = regime;
    this.negociavel = negociavel;
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

}
