package dataAccessPackage;

import exceptionPackage.ConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

public class DBAccess implements DataAccess {
    private Connection connection;

    public DBAccess() throws ConnectionException{
        connection = SingletonConnection.getInstance();
    }

    public void closeConnection() throws ConnectionException{
        try{
            if(connection != null){
                connection.close();
            }
        }catch (SQLException exception){
            throw new ConnectionException(exception.getMessage());
        }
    }
}
