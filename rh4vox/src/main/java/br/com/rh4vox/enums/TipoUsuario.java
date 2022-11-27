package br.com.rh4vox.enums;

public enum TipoUsuario {
  ADM("ADM"),
  GESTOR("GESTOR"),
  RH("RH"),
  CANDIDATO("CANDIDATO");

  private final String text;

  TipoUsuario(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
