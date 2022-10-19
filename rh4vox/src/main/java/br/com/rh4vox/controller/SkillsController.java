package br.com.rh4vox.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;


public class SkillsController implements Initializable {
	
    @FXML
    private Label skillLabel;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void setSkill(String skill) {
      skillLabel.setText(skill);
    }
}