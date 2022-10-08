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
        alert.setContentText("Seja bem vindo!!!");
        alert.show();   
    }

    public void popupCreateJob(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("rh4vox");
        alert.setHeaderText("Vaga registrada com sucesso");
        alert.setContentText("Aguarde candidaturas");
        alert.show();   
    }
}
