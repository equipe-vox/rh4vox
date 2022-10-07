package br.com.rh4vox.dao;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;


public class BaseDAO {

    public Connection getConnection() throws SQLException {
        try{
            Properties properties = new Properties();
    
            properties.load(new FileInputStream("database.properties"));
    
            String url = String.format("jdbc:%s://%s:%s/%s",
                properties.getProperty("driver"), 
                properties.getProperty("host"),
                properties.getProperty("port"),
                properties.getProperty("database")
            );
    
            Connection conn = DriverManager.getConnection(url, properties);
            return conn;
        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
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
