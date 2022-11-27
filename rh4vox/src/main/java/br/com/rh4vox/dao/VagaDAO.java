package br.com.rh4vox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rh4vox.enums.StatusCandidatura;
import br.com.rh4vox.enums.Regime;
import br.com.rh4vox.model.Candidatura;
import br.com.rh4vox.model.CandidaturaRh;
import br.com.rh4vox.model.Usuario;
import br.com.rh4vox.model.Vaga; 

public class VagaDAO extends BaseDAO{
    
    public Vaga getVaga(Integer idVaga) throws SQLException {
        Connection conn = getConnection(); 
    
        String sql = "SELECT * FROM vaga WHERE id=?";

        Vaga vaga = new Vaga();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVaga);
            ResultSet rs = stmt.executeQuery();
      

            while(rs.next()) {
              vaga.setId(rs.getInt("id"));
              vaga.setNome(rs.getString("nome"));
              vaga.setDescricao(rs.getString("descricao"));
              vaga.setSalario(rs.getBigDecimal("salario"));
              vaga.setRegime(Regime.valueOf(rs.getString("regime")));
              vaga.setNegociavel(rs.getBoolean("negociavel"));
              vaga.setAberto(rs.getBoolean("aberto"));
              vaga.setCargo(rs.getString("cargo"));
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vaga;
    }

    public void updateVaga(Vaga vaga) throws SQLException {
        Connection conn = getConnection(); 
        String sql = "UPDATE vaga SET nome=?, descricao=?, regime=?, salario=?, negociavel=?, cargo=? WHERE id=?";
 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, vaga.getNome());
        statement.setString(2, vaga.getDescricao());
        statement.setString(3, vaga.getRegime().toString());
        statement.setBigDecimal(4, vaga.getSalario());
        statement.setBoolean(5,  vaga.getNegociavel());
        statement.setString(6, vaga.getCargo());
        statement.setInt(7, vaga.getId());
        
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing job was updated successfully!");
        }
    }

    public void insertVaga(Vaga vaga) throws SQLException{
        executeQuery(String.format("INSERT INTO vaga (nome, descricao, regime, aberto, salario, negociavel, cargo, id_usuario) VALUES('%s', '%s', '%s', %s, %s, %s, '%s', %s)", vaga.getNome(), vaga.getDescricao(), vaga.getRegime().toString(), vaga.getAberto(), vaga.getSalario(), vaga.getNegociavel(), vaga.getCargo(), vaga.getIdUsuario()));
    }

    public void removeVaga(Integer id) throws SQLException{
        Connection conn = getConnection(); 
        String sqlfk = "DELETE FROM candidato_vaga WHERE id_vaga=?";
        String sql = "DELETE FROM vaga WHERE id=?";
 
        PreparedStatement statementfk = conn.prepareStatement(sqlfk);
        PreparedStatement statement = conn.prepareStatement(sql);
        statementfk.setInt(1, id);
        statement.setInt(1, id);

        statementfk.executeUpdate();
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A job was deleted successfully!");
        }
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
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vagas;
    }

    public void insertCandidatura(Integer idVaga, Integer idCandidato) throws SQLException {
        executeQuery(String.format("INSERT INTO candidato_vaga (id_vaga, id_candidato, status_candidato) VALUES(%s, %s, '%s')", idVaga, idCandidato, StatusCandidatura.ENVIADO));
    }

    public List<Candidatura> getAllCandidaturas() throws SQLException {
        Connection conn = getConnection(); 
    
        List<Candidatura> candidaturas = new ArrayList<>();
        String sql = "SELECT * FROM candidato_vaga";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
      
            while(rs.next()) {
                

              Candidatura c = new Candidatura();
      
              c.setId(rs.getInt("id"));
              c.setIdCandidato(rs.getInt("id_candidato"));
              c.setIdVaga(rs.getInt("id_vaga"));
              c.setStatusCandidato(StatusCandidatura.valueOf((rs.getString("status_candidato"))));
              candidaturas.add(c);
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return candidaturas;
    }

    public List<Candidatura> listCandidaturas(Integer idVaga, Integer idCandidato) throws SQLException {
        Connection conn = getConnection(); 
    
        List<Candidatura> candidaturas = new ArrayList<>();
        String sql = "SELECT * FROM candidato_vaga WHERE id_candidato=? AND id_vaga=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCandidato);
            stmt.setInt(2, idVaga);
            ResultSet rs = stmt.executeQuery();
      
            while(rs.next()) {
                

              Candidatura c = new Candidatura();
      
              c.setId(rs.getInt("id"));
              c.setIdCandidato(rs.getInt("id_candidato"));
              c.setIdVaga(rs.getInt("id_vaga"));
              c.setStatusCandidato(StatusCandidatura.valueOf((rs.getString("status_candidato"))));
              candidaturas.add(c);
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return candidaturas;
    }

    public List<Candidatura> listCandidaturasByCandidato(Integer idCandidato) throws SQLException {
        Connection conn = getConnection(); 
    
        List<Candidatura> candidaturas = new ArrayList<>();
        String sql = "SELECT * FROM candidato_vaga WHERE id_candidato=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCandidato);
            ResultSet rs = stmt.executeQuery();
      
            while(rs.next()) {
                

              Candidatura c = new Candidatura();
      
              c.setId(rs.getInt("id"));
              c.setIdCandidato(rs.getInt("id_candidato"));
              c.setIdVaga(rs.getInt("id_vaga"));
              c.setStatusCandidato(StatusCandidatura.valueOf((rs.getString("status_candidato"))));
              candidaturas.add(c);
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return candidaturas;
    }

    public List<Candidatura> listCandidaturasByVaga(Integer idVaga) throws SQLException {
        Connection conn = getConnection(); 
    
        List<Candidatura> candidaturas = new ArrayList<>();
        String sql = "SELECT * FROM candidato_vaga WHERE id_vaga=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVaga);
            ResultSet rs = stmt.executeQuery();
      
            while(rs.next()) {
                

              Candidatura c = new Candidatura();
      
              c.setId(rs.getInt("id"));
              c.setIdCandidato(rs.getInt("id_candidato"));
              c.setIdVaga(rs.getInt("id_vaga"));
              c.setStatusCandidato(StatusCandidatura.valueOf((rs.getString("status_candidato"))));
              candidaturas.add(c);
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return candidaturas;
    }

    public List<Vaga> listVagasByUsuario(Integer idUsuario) throws SQLException{
        Connection conn = getConnection(); 
    
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga WHERE id_usuario=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
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
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vagas;
    }

    public List<Vaga> listVagasByNome(String nome) throws SQLException{
        Connection conn = getConnection(); 
    
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga WHERE nome LIKE '%"+nome+"%'";

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
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vagas;
    }

    public List<CandidaturaRh> listCandidaturasByRh(Integer idRh) throws SQLException{
        Connection conn = getConnection(); 
    
        List<CandidaturaRh> candidaturas = new ArrayList<>();

        String sql = "SELECT * FROM candidato_vaga ";
        sql += "INNER JOIN vaga ON vaga.id = candidato_vaga.id_vaga ";
        sql += "INNER JOIN candidato ON candidato.id = candidato_vaga.id_candidato ";
        sql += "INNER JOIN curriculo ON curriculo.id_candidato = candidato_vaga.id_candidato ";
        sql += "WHERE vaga.id_usuario="+idRh;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
      
            while(rs.next()) {
              CandidaturaRh c = new CandidaturaRh();
      
              c.setIdVaga(rs.getInt("id_vaga"));
              c.setNomeVaga(rs.getString("nome"));
              c.setDescricao(rs.getString("descricao"));
              c.setSalario(rs.getBigDecimal("salario"));
              c.setRegime(Regime.valueOf(rs.getString("regime")));
              c.setNegociavel(rs.getBoolean("negociavel"));
              c.setCargo(rs.getString("cargo"));
              c.setStatus(StatusCandidatura.valueOf(rs.getString("status_candidato")));

              c.setIdCandidato(rs.getInt("id_candidato"));
              c.setNomeCand(rs.getString("nome_candidato"));
              c.setDataNasc((rs.getDate("data_nasc").toLocalDate()));
              c.setObjetivo(rs.getString("objetivo"));
              c.setHabilidades((rs.getString("habilidades")));
              c.setTelefone(rs.getString("telefone"));
              c.setFormacao(rs.getString("formacao"));
              c.setExperiencia(rs.getString("experiencia"));

              candidaturas.add(c);
            }
      
            stmt.close();
            rs.close();
            conn.close();
      
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return candidaturas;
    }

    public void updateCandidacyStatus(Integer idCandidato, Integer idVaga, StatusCandidatura status) throws SQLException {
        Connection conn = getConnection(); 
        String sql = "UPDATE candidato_vaga SET status_candidato=? WHERE id_candidato=? AND id_vaga=?";
 
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, status.toString());
        statement.setInt(2, idCandidato);
        statement.setInt(3, idVaga);
        
        statement.executeUpdate();
    }

    public List<CandidaturaRh> approvedCandidacies(Usuario usuario) throws SQLException {
        
        Connection conn = getConnection(); 
        
        List<CandidaturaRh> candidaturas = new ArrayList<>();
        List<CandidaturaRh> approvedCandidacies = new ArrayList<>();
    
        String sql = "SELECT * FROM candidato_vaga ";
        sql += "INNER JOIN vaga ON vaga.id = candidato_vaga.id_vaga ";
        sql += "INNER JOIN candidato ON candidato.id = candidato_vaga.id_candidato ";
        sql += "INNER JOIN curriculo ON curriculo.id_candidato = candidato_vaga.id_candidato ";
        sql += "WHERE vaga.id_usuario="+usuario.getId();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
      
            while(rs.next()) {
              CandidaturaRh c = new CandidaturaRh();
      
              c.setIdVaga(rs.getInt("id_vaga"));
              c.setNomeVaga(rs.getString("nome"));
              c.setDescricao(rs.getString("descricao"));
              c.setSalario(rs.getBigDecimal("salario"));
              c.setRegime(Regime.valueOf(rs.getString("regime")));
              c.setNegociavel(rs.getBoolean("negociavel"));
              c.setCargo(rs.getString("cargo"));
              c.setStatus(StatusCandidatura.valueOf(rs.getString("status_candidato")));

              c.setIdCandidato(rs.getInt("id_candidato"));
              c.setNomeCand(rs.getString("nome_candidato"));
              c.setDataNasc((rs.getDate("data_nasc").toLocalDate()));
              c.setObjetivo(rs.getString("objetivo"));
              c.setHabilidades((rs.getString("habilidades")));
              c.setTelefone(rs.getString("telefone"));
              c.setFormacao(rs.getString("formacao"));
              c.setExperiencia(rs.getString("experiencia"));

              candidaturas.add(c);
            }
      
            stmt.close();
            rs.close();
            conn.close();

            for(CandidaturaRh crh:candidaturas) {
                if(crh.getStatus() == StatusCandidatura.APROVADO) {
                    approvedCandidacies.add(crh);
                }
            }
      
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return approvedCandidacies;
    }
}
