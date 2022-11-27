package br.com.rh4vox.validator;

import br.com.rh4vox.exception.ValidationException;

public class EmailValidator{

    public static void validate(String value) throws ValidationException {
        String exceptionMessage = "E-mail inválido";

        if(value.startsWith("@")
         || value.startsWith(".")
         || value.endsWith("@")
         || value.endsWith(".") 
         || !(value.contains("@") && value.contains("."))){
            throw new ValidationException(exceptionMessage);
        }
    }
    
}
