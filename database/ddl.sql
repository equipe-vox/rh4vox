CREATE TABLE vaga (
	id SERIAL PRIMARY KEY,
	nome VARCHAR NOT NULL,
	descricao TEXT NOT NULL,
	salario NUMERIC NOT NULL,
	regime VARCHAR NOT NULL,
	aberto BOOLEAN NOT NULL,
	negociavel BOOLEAN NOT NULL,
	cargo VARCHAR NOT NULL
);

CREATE TABLE usuario (
	id SERIAL PRIMARY KEY,
	email TEXT NOT NULL UNIQUE,
	senha VARCHAR NOT NULL,
	tipo VARCHAR NOT NULL
);

CREATE TABLE candidato (
	id SERIAL PRIMARY KEY,
	nome_candidato TEXT NOT NULL,
	data_nasc DATE NOT NULL,
	cpf VARCHAR NOT NULL,
	id_usuario INTEGER NOT NULL,
	telefone VARCHAR,
	FOREIGN KEY (id_usuario) 
          REFERENCES usuario (id)	
);

CREATE TABLE curriculo (
	id SERIAL PRIMARY KEY,
	bio VARCHAR NOT NULL,
	objetivo VARCHAR NOT NULL,
	habilidades VARCHAR NOT NULL,
	formacao VARCHAR NOT NULL,
	experiencia VARCHAR NOT NULL,
	id_candidato INTEGER NOT NULL,
	FOREIGN KEY (id_candidato) 
          REFERENCES candidato (id)
);

CREATE TABLE candidato_vaga (
	id SERIAL PRIMARY KEY,
	id_candidato INTEGER NOT NULL,
	id_vaga INTEGER NOT NULL,
	status_candidato VARCHAR NOT NULL,
	FOREIGN KEY (id_candidato) 
          REFERENCES candidato (id),
	FOREIGN KEY (id_vaga) 
          REFERENCES vaga (id)
);

CREATE TABLE usuario_vaga (
	id SERIAL PRIMARY KEY,
	id_usuario INTEGER NOT NULL,
	id_vaga INTEGER NOT NULL,
	FOREIGN KEY (id_usuario) 
          REFERENCES usuario (id),
	FOREIGN KEY (id_vaga) 
          REFERENCES vaga (id)
);

CREATE TABLE rh (
	id SERIAL PRIMARY KEY,
	nome TEXT NOT NULL,
	cpf VARCHAR NOT NULL,
	id_usuario INTEGER NOT NULL,
	FOREIGN KEY (id_usuario) 
          REFERENCES usuario (id)	
);

CREATE TABLE administrador (
	id SERIAL PRIMARY KEY,
	nome TEXT NOT NULL,
	cpf VARCHAR NOT NULL,
	id_usuario INTEGER NOT NULL,
	FOREIGN KEY (id_usuario) 
          REFERENCES usuario (id)	
);

CREATE TABLE gestor (
	id SERIAL PRIMARY KEY,
	nome TEXT NOT NULL,
	cpf VARCHAR NOT NULL,
	id_usuario INTEGER NOT NULL,
	FOREIGN KEY (id_usuario) 
          REFERENCES usuario (id)	
);
