package dataAccessPackage;

import modelPackage.Order;
import exceptionPackage.*;
import modelPackage.OrderResearch;

import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderDataAccess {

    Integer getMaxNumber(Integer client) throws AllValuesException;
    Integer getOrderId(Integer number, Integer customer) throws AllValuesException;
    void addNewOrder(Order order) throws AddValueException;
    ArrayList<OrderResearch> getOrderResearch(Integer customer_id, LocalDate firstDate, LocalDate secondDate) throws AllValuesException;

}
