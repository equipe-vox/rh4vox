package br.com.rh4vox;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rh4vox.exception.ValidationException;
import br.com.rh4vox.validator.SenhaValidator;

public class SenhaValidatorTest {
    
    @Test
    public void validPasswordTest(){
        try{
            String senha = "teste123";
            SenhaValidator.validate(senha);
            assertTrue(true);
        }catch(ValidationException exception){
            assertTrue(true);
        }
    }

    @Test
    public void shortPasswordTest(){
        try{
            String senha = "teste";
            SenhaValidator.validate(senha);
            assertTrue(false);
        }catch(ValidationException exception){
            assertTrue(false);
        }
    }
}
