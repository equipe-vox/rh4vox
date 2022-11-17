package br.com.rh4vox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.rh4vox.validator.EmailValidator;

public class EmailValidatorTest {
    
    @Test
    public void validEmailTest(){
        String email = "teste@teste.com";

        assertTrue(EmailValidator.validate(email));
    }

    @Test
    public void emailWithoutAtSignTest(){
        String email = "testeteste.com";

        assertFalse(EmailValidator.validate(email));
    }

    @Test
    public void emailWithoutDomainTest(){
        String email = "teste@";

        assertFalse(EmailValidator.validate(email));
    }

    @Test
    public void emailWithOnlyDomainTest(){
        String email = "@teste.com";

        assertFalse(EmailValidator.validate(email));
    }

    @Test
    public void emailWithIncompleteDomainTest(){
        String email = "teste@teste";

        assertFalse(EmailValidator.validate(email));
    }
}
