package br.com.rh4vox.enums;

public enum StatusCandidatura {
  ENVIADO("ENVIADO"),
  RECUSADO("RECUSADO"),
  APROVADO("APROVADO");

  private final String text;

  StatusCandidatura(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
