package br.com.rh4vox.dao;

import java.sql.*;
import java.util.Properties;

public class BaseDAO {

    public Connection getConnection() throws SQLException{
        String url = "jdbc:postgresql://localhost/rh4vox";
        Properties props = new Properties();
        
        props.setProperty("user", "postgres");
        props.setProperty("password", "password");
        props.setProperty("ssl", "true");

        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }

    public void executeQuery(String query) throws SQLException{
        try{
            Connection conn = getConnection(); 
            Statement st = conn.createStatement();
    
            st.execute(query);
    
            st.close();
            conn.close();
            
        } catch (SQLException e){
            e.printStackTrace();
        } 
    }
    
}
