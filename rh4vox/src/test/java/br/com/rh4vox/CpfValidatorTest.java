package br.com.rh4vox;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rh4vox.exception.ValidationException;
import br.com.rh4vox.validator.CpfValidator;

public class CpfValidatorTest {

    @Test
    public void ValidCpfTest(){
        String cpfValido = "522.679.358-80";
        CpfValidator.validate(cpfValido);
        
        assertTrue(true);
    }

    @Test
    public void InvalidCpfTest(){
        String cpfInvalido = "522.678.358-80";

        assertThrows(ValidationException.class, () -> CpfValidator.validate(cpfInvalido));
    }

    @Test
    public void CpfWithSameNumbersTest(){
        String cpfInvalido = "000.000.000-00";
        
        assertThrows(ValidationException.class, () -> CpfValidator.validate(cpfInvalido));
    }

}
