package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rh4vox.enums.Regime;
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

    public List<Vaga> listVagas() throws SQLException{
        Connection conn = getConnection(); 
    
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
      
            while(rs.next()) {
              Vaga v = new Vaga();
      
              v.setId(rs.getInt("id"));
              v.setNome(rs.getString("nome"));
              v.setDescricao(rs.getString("descricao"));
              v.setSalario(rs.getBigDecimal("salario"));
              v.setRegime(Regime.valueOf(rs.getString("regime")));
              v.setNegociavel(rs.getBoolean("negociavel"));
              v.setAberto(rs.getBoolean("aberto"));
              v.setCargo(rs.getString("cargo"));
              vagas.add(v);
              break;
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vagas;
    }
}
