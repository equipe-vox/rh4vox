package br.com.rh4vox.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import br.com.rh4vox.model.*;

public class VagaExtractor {
	
	private String filename;
	private String text;
	
	public VagaExtractor(String filename) {
		this.filename = filename;
		
		text = "Nome;Salário;Descrição;Regime;\n";
	}
	
	public void extractVagas(List<Vaga> vagas) {
		for(Vaga vaga : vagas) {
			text += vaga2Text(vaga) + "\n";
		}
		
		writeText(text);
	}
	
	public String vaga2Text(Vaga vaga){
		String line = new String();
		
		line += vaga.getNome() + ";";
		line += vaga.getSalario() + ";";
		line += vaga.getDescricao() + ";";
		line += vaga.getRegime() + ";";
		
		return line;
	}
	
	private void writeText(String text) {
		try {
			FileWriter csvFile = new FileWriter(filename);
			
			csvFile.write(text);
			csvFile.close();
			
		} catch (IOException e) {
			System.out.println("Erro ao esrever em arquivo CSV.");
			e.printStackTrace();
		}
	}
	
}
