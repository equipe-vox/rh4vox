package rh4vox.rh4vox.service;

import rh4vox.rh4vox.model.BaseVagas;
import rh4vox.rh4vox.model.Vaga;

public class VagaService {
  public void saveJob(Vaga vaga) {
		BaseVagas.getInstance().addVaga(vaga);

		new VagaExtractor("vagas.csv").extractVagas(BaseVagas.getInstance().getVagas());

    
  }
}
