package br.com.rh4vox.service;

import br.com.rh4vox.model.*;

public class VagaService {
  public void saveJob(Vaga vaga) {
		BaseVagas.getInstance().addVaga(vaga);

		new VagaExtractor("vagas.csv").extractVagas(BaseVagas.getInstance().getVagas());

    
  }
}
