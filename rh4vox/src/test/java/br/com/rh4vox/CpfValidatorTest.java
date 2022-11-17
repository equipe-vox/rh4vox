package br.com.rh4vox;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rh4vox.exception.ValidationException;
import br.com.rh4vox.validator.CpfValidator;

public class CpfValidatorTest {

    @Test
    public void ValidCpfTest(){
        try{
            String cpfValido = "522.679.358-80";
            CpfValidator.validate(cpfValido);
            assertTrue(true);
        } catch (ValidationException exception) {
            assertTrue(false);
        }
    }

    @Test
    public void InvalidCpfTest(){
        try{
            String cpfValido = "522.678.358-80";
            CpfValidator.validate(cpfValido);
            assertTrue(false);
        } catch (ValidationException exception) {
            assertTrue(true);
        }
    }

    @Test
    public void CpfWithSameNumbersTest(){
        try{
            String cpfValido = "000.000.000-00";
            CpfValidator.validate(cpfValido);
            assertTrue(false);
        } catch (ValidationException exception) {
            assertTrue(true);
        }
    }

}
