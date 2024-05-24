package businessPackage;

import dataAccessPackage.*;
import exceptionPackage.*;
import modelPackage.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class OrderManager {
    private OrderDataAccess dao;
    public OrderManager() throws ConnectionException {
        setDao(new OrderDBAccess());
    }
    public void setDao(OrderDataAccess dao) {
        this.dao = dao;
    }
    public Integer getMaxNumber(Integer client) throws AllValuesException{
        return dao.getMaxNumber(client);
    }
    public Integer getOrderId(Integer number, Integer customer) throws AllValuesException{
        return dao.getOrderId(number,customer);
    }
    public void addNewOrder(Order order) throws AddValueException{
        dao.addNewOrder(order);
    }
    public ArrayList<OrderResearch> getOrderResearch(Integer customer_id, LocalDate firstDate, LocalDate secondDate) throws AllValuesException{
        return dao.getOrderResearch(customer_id,firstDate,secondDate);
    }
}
