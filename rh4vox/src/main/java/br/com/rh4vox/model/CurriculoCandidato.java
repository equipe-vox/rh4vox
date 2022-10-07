package br.com.rh4vox.model;

public class CurriculoCandidato {
  private static CurriculoCandidato instance;
  private Curriculo curriculo;

  private CurriculoCandidato () {
    
  }

  public static CurriculoCandidato getInstance() {
    
    if(instance == null) {
      instance = new CurriculoCandidato();
    }

    return instance;
  }

  public void setCurriculo(Curriculo curriculo) {
    this.curriculo = curriculo;
  }

  public Curriculo getCurriculo() {
    return curriculo;
  }

  public void setIdCurriculo(Integer idCurriculo) {
    this.curriculo.setId(idCurriculo);
  }

  public Integer getIdCurriculo() {
    return curriculo.getId();
  }
}
