package br.com.rh4vox.model;

import java.math.BigDecimal;

import br.com.rh4vox.enums.Regime;

public class Vaga {
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal salario;
    private Regime regime;
    private Boolean negociavel;
    private Boolean aberto;
    private String cargo;
    private Integer idUsuario;

    public Vaga(
        Integer id, 
        String nome, 
        String descricao, 
        BigDecimal salario, 
        Regime regime, 
        Boolean negociavel, 
        Boolean aberto, 
        String cargo,
        Integer idUsuario
    ){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.salario = (BigDecimal) salario;
        this.regime = regime;
        this.negociavel = negociavel;
        this.aberto = aberto;
        this.cargo = cargo;
        this.idUsuario = idUsuario;
    }

    public Vaga() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public Boolean getAberto() {
        return aberto;
    }

    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }

    public Boolean getNegociavel() {
        return negociavel;
    }

    public void setNegociavel(Boolean negociavel) {
        this.negociavel = negociavel;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
