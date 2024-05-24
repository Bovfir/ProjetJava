package dataAccessPackage;

import exceptionPackage.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;

    private SingletonConnection() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pocket_bakery", "root", "root");
    }

    public static Connection getInstance() throws ConnectionException {
        if(connection == null){
            try {
                new SingletonConnection();
            }catch (SQLException exception){
                throw new ConnectionException(exception.getMessage());
            }
        }
        return connection;
    }
}
