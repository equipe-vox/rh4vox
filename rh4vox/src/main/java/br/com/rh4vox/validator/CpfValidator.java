package br.com.rh4vox.validator;

import br.com.rh4vox.exception.ValidationException;

public class CpfValidator{

    public static void validate(String cpf) throws ValidationException {
        String exceptionMessage = "CPF inválido";
        String cpfNumbers = cpf.replace(".", "").replace("-", "");
        int primeiroDigito = Character.getNumericValue(cpfNumbers.charAt(9));
        int segundoDigito = Character.getNumericValue(cpfNumbers.charAt(10));
        int somaPrimeiroDigito = 0, somaSegundoDigito = 0, sobra;

        Integer number = null;
        for(int i=0; i<11; i++){
          if(number == null)
            number = Character.getNumericValue(cpfNumbers.charAt(i));

          if(number != Character.getNumericValue(cpfNumbers.charAt(i)))
            break;

          if(i == 10)
            throw new ValidationException(exceptionMessage);
        }
        
        for(int i=0; i<9; i++){
          int numberAtPosition = Character.getNumericValue(cpfNumbers.charAt(i));
          somaPrimeiroDigito += numberAtPosition * (10-i);
        }
    
        sobra = 11 - (somaPrimeiroDigito % 11);
    
        if(sobra > 10 && primeiroDigito != 0 || sobra < 10 && sobra != primeiroDigito){
          throw new ValidationException(exceptionMessage);
        }
    
        for(int i=0; i<10; i++){
          int numberAtPosition = Character.getNumericValue(cpfNumbers.charAt(i));
          somaSegundoDigito += numberAtPosition * (11-i);
        }
    
        sobra = 11 - (somaSegundoDigito % 11);
    
        if(sobra > 10 && segundoDigito != 0 || sobra < 10 && sobra != segundoDigito){
            throw new ValidationException(exceptionMessage);
        }
    }
    
}
