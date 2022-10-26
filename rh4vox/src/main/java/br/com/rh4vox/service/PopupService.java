package br.com.rh4vox.service;

import javafx.scene.control.Alert;

public class PopupService {
    public void popupSignUp(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Cadastro realizado com sucesso.");
        alert.setContentText("Realize login com as credenciais cadastradas.");
        alert.show();   
    }

    public void popupLogin(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Login realizado com sucesso");
        alert.setContentText("Seja bem vindo!");
        alert.show();   
    }

    public void popupCreateJob(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Vaga registrada com sucesso");
        alert.setContentText("Aguarde candidaturas");
        alert.show();   
    }



    public void popupSaveCurriculo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Salvo com sucesso");
        alert.setContentText("Seu currículo foi salvo, altere suas informações se desejar");
        alert.show();   
    }

    public void popupEmptyInput(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Erro");
        alert.setContentText("Um ou mais campos não podem estar em branco");
        alert.show();   
    }

    public void popupPassword(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Erro");
        alert.setContentText("A senha precisa ter de 6 a 20 caracteres");
        alert.show();   
    }

    public void popupEmailAlreadyInUse(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Erro");
        alert.setContentText("Este email já está em uso");
        alert.show();   
    }

    public void popupCpfAlreadyExists(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Erro");
        alert.setContentText("Este CPF já existe em nosso sistema");
        alert.show();   
    }

    public void popupIncorrectEmailOrPass() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Erro");
        alert.setContentText("Email ou senha estão incorretos");
        alert.show(); 
    }

    public void popupCapitalLetterPass() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Erro");
        alert.setContentText("Sua senha precisa ter pelo menos 1 letra maíuscula");
        alert.show(); 
    }
    
}
