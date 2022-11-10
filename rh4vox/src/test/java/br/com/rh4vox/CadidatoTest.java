package br.com.rh4vox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rh4vox.service.CandidatoService;

public class CadidatoTest {

    @Test
    public void CandidatoValidCpfTest(){
        CandidatoService candidatoService = new CandidatoService();
        String cpfValido = "522.679.358-80";

        assertTrue(candidatoService.checkCpf(cpfValido));
    }

    @Test
    public void CandidatoInvalidCpfTest(){
        CandidatoService candidatoService = new CandidatoService();
        String cpfValido = "522.678.358-80";

        assertFalse(candidatoService.checkCpf(cpfValido));
    }

    @Test
    public void CpfWithSameNumbersTest(){
        CandidatoService candidatoService = new CandidatoService();
        String cpfValido = "000.000.000-00";

        assertFalse(candidatoService.checkCpf(cpfValido));
    }

}
