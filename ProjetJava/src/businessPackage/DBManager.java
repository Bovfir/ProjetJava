package businessPackage;

import dataAccessPackage.DBAccess;
import dataAccessPackage.DataAccess;
import exceptionPackage.ConnectionException;

public class DBManager {
    private DataAccess dataAccess;

    public DBManager() throws ConnectionException{
        dataAccess = new DBAccess();
    }

    public void closeConnection() throws ConnectionException{
        dataAccess.closeConnection();
    }
}
