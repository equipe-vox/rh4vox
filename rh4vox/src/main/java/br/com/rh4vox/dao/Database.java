package br.com.rh4vox.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import br.com.rh4vox.exception.DatabaseNotAvailableException;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database(){}

    private Properties getDatabasePropeties() throws IOException{
        Properties properties = new Properties();
        properties.load(new FileInputStream("database.properties"));
        return properties;
    }

    private void connect(){
        try{
            if(connection == null || connection.isClosed()){
                Properties properties = getDatabasePropeties();

                String connectionString = String.format("jdbc:%s://%s:%s/%s",
                    properties.getProperty("driver"), 
                    properties.getProperty("host"),
                    properties.getProperty("port"),
                    properties.getProperty("database")
                );
    
                connection = DriverManager.getConnection(connectionString, properties);
            }
        } catch(Exception e){
            e.printStackTrace();
            throw new DatabaseNotAvailableException();
        }
    }

    public static Database getInstance(){
        if(instance == null)
            instance = new Database();
        
            return instance;
    }

    public Connection getConnection(){
        connect();
        return connection;
    }
}
