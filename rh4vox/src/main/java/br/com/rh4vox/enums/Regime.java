package br.com.rh4vox.enums;

public enum Regime {
  CLT("CLT"),
  PJ("PJ"),
  ESTAGIO("ESTAGIO");

  private final String text;

  Regime(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
