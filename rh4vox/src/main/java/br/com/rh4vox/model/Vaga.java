package br.com.rh4vox.model;

import br.com.rh4vox.enums.Regime;

public class Vaga {
    private String nome;
    private String descricao;
    private String salario;
    private Regime regime;
    private Boolean negociavel;
    private String cargo;

    public Vaga(String nome, String descricao, String salario, Regime regime){
        this.nome = nome;
        this.descricao = descricao;
        this.salario = salario;
        this.regime = regime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }
    
}
