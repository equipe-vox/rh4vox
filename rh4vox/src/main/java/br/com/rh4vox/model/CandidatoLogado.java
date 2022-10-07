package br.com.rh4vox.model;

public class CandidatoLogado {
  private static CandidatoLogado instance;
  private Candidato candidato;

  private CandidatoLogado () {
    
  }

  public static CandidatoLogado getInstance() {
    
    if(instance == null) {
      instance = new CandidatoLogado();
    }

    return instance;
  }

  public void setCandidato(Candidato candidato) {
    this.candidato = candidato;
  }

  public Candidato getCandidato() {
    return candidato;
  }

  public void logoff() {
    this.candidato = null;
  }
}
