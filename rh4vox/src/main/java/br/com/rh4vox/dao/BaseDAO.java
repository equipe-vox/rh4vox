package br.com.rh4vox.dao;

import java.sql.*;
import java.util.Properties;

public class BaseDAO {

    public Connection getConnection() throws SQLException{
        String url = "jdbc:postgresql://localhost:5432/testRH4Vox";
        Properties props = new Properties();
        
        props.setProperty("user", "postgres");
        props.setProperty("password", "130204jv");
        props.setProperty("ssl", "false");

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
