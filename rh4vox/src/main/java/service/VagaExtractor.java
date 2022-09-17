package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Vaga;

public class VagaExtractor {
	
	private String filename;
	
	public VagaExtractor(String filename) {
		this.filename = filename;
		
		File csvFile = new File(filename);
		csvFile.delete();
		
		writeLine("Nome;Salário;Descrição;Regime;");
		
		System.out.println(filename);
	}
	
	public void extract(Vaga vaga){
		String line = new String();
		line += vaga.getNome() + ";";
		line += vaga.getSalario() + ";";
		line += vaga.getDescricao() + ";";
		line += vaga.getRegime() + ";";
		
		writeLine(line);
	}
	
	private void writeLine(String line) {
		try {
			FileWriter csvFile = new FileWriter(filename);
			
			csvFile.write(line);
			csvFile.close();
			
		} catch (IOException e) {
			System.out.println("Erro ao extrair vaga em arquivo CSV.");
			e.printStackTrace();
		}
	}
	
}
