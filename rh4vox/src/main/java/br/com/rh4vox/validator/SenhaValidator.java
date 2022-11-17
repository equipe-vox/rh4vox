package br.com.rh4vox.validator;

import br.com.rh4vox.exception.ValidationException;

public class SenhaValidator {
    
    public static void validate(String senha) throws ValidationException{
        String exceptionMessage = "Senha inválida: ";

        if(senha.length() < 6)
            throw new ValidationException(exceptionMessage + "menor que 6 caracteres");
    }

}
