package br.com.rh4vox;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rh4vox.exception.ValidationException;
import br.com.rh4vox.validator.EmailValidator;

public class EmailValidatorTest {
    
    @Test
    public void validEmailTest(){
        String email = "teste@teste.com";
        EmailValidator.validate(email);

        assertTrue(true);
    }

    @Test
    public void emailWithoutAtSignTest(){
        String email = "testeteste.com";

        assertThrows(ValidationException.class, () -> EmailValidator.validate(email));
    }

    @Test
    public void emailWithoutDomainTest(){
        String email = "teste@";

        assertThrows(ValidationException.class, () -> EmailValidator.validate(email));
    }

    @Test
    public void emailWithOnlyDomainTest(){
        String email = "@teste.com";

        assertThrows(ValidationException.class, () -> EmailValidator.validate(email));
    }

    @Test
    public void emailWithIncompleteDomainTest(){
        String email = "teste@teste";

        assertThrows(ValidationException.class, () -> EmailValidator.validate(email));
    }
}
