package rh4vox.model;

public class Vaga {
    private String nome;
    private String descricao;
    private Float salario;
    private String regime;

    public Vaga(String nome, String descricao, Float salario, String regime){
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

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }
    
}
