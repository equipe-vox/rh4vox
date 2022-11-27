package br.com.rh4vox;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rh4vox.exception.ValidationException;
import br.com.rh4vox.validator.SenhaValidator;

public class SenhaValidatorTest {
    
    @Test
    public void validPasswordTest(){
        String senha = "teste123";
        SenhaValidator.validate(senha);

        assertTrue(true);
    }

    @Test
    public void shortPasswordTest(){
        String senha = "teste";

        assertThrows(ValidationException.class, () -> SenhaValidator.validate(senha));
    }
}
