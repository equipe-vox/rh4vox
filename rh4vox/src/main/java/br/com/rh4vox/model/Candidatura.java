package br.com.rh4vox.model;

import br.com.rh4vox.enums.StatusCandidatura;

public class Candidatura {
  private Integer id;
  private Integer idVaga;
  private Integer idCandidato;
  private StatusCandidatura statusCandidato;

  public Candidatura(
    Integer id,
    Integer idVaga,
    Integer idCandidato,
    StatusCandidatura statusCandidato
  ) {
    this.id = id;
    this.idVaga = idVaga;
    this.idCandidato = idCandidato;
    this.statusCandidato = statusCandidato;
  }

  public Candidatura() {}

  
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdVaga() {
		return this.idVaga;
	}

	public void setIdVaga(Integer idVaga) {
		this.idVaga = idVaga;
	}

	public Integer getIdCandidato() {
		return this.idCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}

	public StatusCandidatura getStatusCandidato() {
		return this.statusCandidato;
	}

	public void setStatusCandidato(StatusCandidatura statusCandidato) {
		this.statusCandidato = statusCandidato;
	}
}
