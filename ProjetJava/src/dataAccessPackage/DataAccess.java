package dataAccessPackage;

import exceptionPackage.ConnectionException;

public interface DataAccess {
    void closeConnection()throws ConnectionException;
}
