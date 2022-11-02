package br.com.rh4vox.model;

public class RH {
  private Integer id;
  private String nome;
  private String cpf;
  private Integer idUsuario;

  public RH(
    Integer id,
    String nome,
    String cpf,
    Integer idUsuario
  ) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.idUsuario = idUsuario;
  }

  public RH() {}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
}
