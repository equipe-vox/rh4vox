package br.com.rh4vox.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.rh4vox.model.Vaga; 

public class VagaDAO extends BaseDAO{
    
    public Vaga getVaga(int id) {
        return null;
    }

    public void updateVaga(Vaga vaga) {

    }

    public void insertVaga(Vaga vaga) throws SQLException{
        executeQuery(String.format("INSERT INTO vaga (nome, descricao, regime, aberto, salario, negociavel, cargo) VALUES('%s', '%s', '%s', %s, %s, %s, '%s')", vaga.getNome(), vaga.getDescricao(), vaga.getRegime().toString(), vaga.getAberto(), vaga.getSalario(), vaga.getNegociavel(), vaga.getCargo()));
    }

    public void removeVaga(Vaga vaga){

    }

    public List<Vaga> listVagas(){
        return null;
    }
}
