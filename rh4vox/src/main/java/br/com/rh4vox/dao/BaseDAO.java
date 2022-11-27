package br.com.rh4vox.dao;
import java.sql.*;

import br.com.rh4vox.exception.DatabaseNotAvailableException;


public class BaseDAO {

    protected PreparedStatement getPreparedStatement(String query) throws DatabaseNotAvailableException, SQLException{
        PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(query);

        return preparedStatement;
    }

    protected void executeQuery(String query) throws SQLException{
        try{
            Statement st = Database.getInstance().getConnection().createStatement();
            st.execute(query);
            st.close();
        } catch (SQLException e){
            e.printStackTrace();
        } 
    }
}
