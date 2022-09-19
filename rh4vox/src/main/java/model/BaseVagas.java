package model;

import java.util.ArrayList;
import java.util.List;

public class BaseVagas {
	private static BaseVagas instance;

	private List<Vaga> vagas;
	
	private BaseVagas() {
		vagas = new ArrayList<Vaga>();
	}
	
	public static BaseVagas getInstance() {
		if(instance == null) {
			instance = new BaseVagas();
		}
		
		return instance;
	}
	
	public void addVaga(Vaga vaga) {
		vagas.add(vaga);
	}
	
	public List<Vaga> getVagas(){
		return vagas;
	}
}
