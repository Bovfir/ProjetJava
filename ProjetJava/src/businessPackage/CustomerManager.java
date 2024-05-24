package businessPackage;

import dataAccessPackage.*;
import modelPackage.*;
import exceptionPackage.*;
import java.util.ArrayList;

public class CustomerManager {
    private CustomerDataAccess dao;

    public CustomerManager() throws ConnectionException {
        setDao(new CustomerDBAccess());
    }
    public void setDao(CustomerDataAccess dao) {
        this.dao = dao;
    }
    public ArrayList<Customer> getAllCustomers() throws AllValuesException {
        return dao.getAllCustomers();
    }
}
