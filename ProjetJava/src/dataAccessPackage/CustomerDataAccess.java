package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.Customer;

import java.util.ArrayList;

public interface CustomerDataAccess {
    ArrayList<Customer> getAllCustomers() throws AllValuesException;
}
