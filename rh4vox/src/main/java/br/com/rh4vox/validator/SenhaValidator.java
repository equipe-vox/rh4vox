package br.com.rh4vox.validator;

import br.com.rh4vox.exception.ValidationException;

public class SenhaValidator {
    
    public static void validate(String senha) throws ValidationException{
        String exceptionMessage = "Senha inválida: ";

        if(senha.length() < 6 || senha.length() > 20)
            throw new ValidationException(exceptionMessage + "A senha precisa ter de 6 a 20 caracteres");
    }

}