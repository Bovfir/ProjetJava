package dataAccessPackage;

import exceptionPackage.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import modelPackage.*;
public class OrderDBAccess implements OrderDataAccess {
    private Connection connection;
    public OrderDBAccess() throws ConnectionException {
            connection = SingletonConnection.getInstance();
    }
    @Override
    public Integer getMaxNumber(Integer client) throws AllValuesException {
        try {
            String sqlinstruction = "SELECT max(number) FROM command WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlinstruction);
            preparedStatement.setInt(1, client);
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            Integer maxNumber = data.getInt("max(number)");
            return maxNumber;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
    @Override
    public Integer getOrderId(Integer number, Integer customer) throws AllValuesException {
        try {
            String sqlInstruction = "SELECT order_number FROM command WHERE number = ? AND customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, number);
            preparedStatement.setInt(2, customer);
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            Integer orderId = data.getInt("order_number");
            return orderId;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
    @Override
    public void addNewOrder(Order order) throws AddValueException {
        try {
            Date date = Date.valueOf(order.getOrderDate());
            String sqlInstruction = " INSERT INTO command (order_date,status_id,customer_id, number, means_of_payment_id) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, order.getCustomer());
            preparedStatement.setInt(4, order.getNumber());
            preparedStatement.setInt(5, order.getMeansOfPayment());
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            throw new AddValueException(exception.getMessage());
        }
    }
    @Override
    public ArrayList<OrderResearch> getOrderResearch(Integer customer_id, LocalDate firstDate, LocalDate secondDate) throws AllValuesException {
        try{
            ArrayList<OrderResearch> orderResearches = new ArrayList<>();

            String sqlInstruction = "SELECT c.order_number, c.order_date, s.wording as 'status', m.wording as 'payment'\n" +
                    "FROM command c inner join status s on c.status_id = s.status_id\n" +
                    "inner join means_of_payment m on c.means_of_payment_id = m.means_of_payment_id\n" +
                    "WHERE c.order_date between ? and ? \n" +
                    "AND c.customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDate(1, java.sql.Date.valueOf(firstDate));
            preparedStatement.setDate(2, java.sql.Date.valueOf(secondDate));
            preparedStatement.setInt(3, customer_id);
            ResultSet data = preparedStatement.executeQuery();

            OrderResearch orderResearch;
            while(data.next()){
                Integer orderNumber = data.getInt("order_number");
                String status = data.getString("status");
                LocalDate orderDate = data.getDate("order_date").toLocalDate();
                String payment = data.getString("payment");

                orderResearch = new OrderResearch(orderNumber, status, orderDate,payment);
                orderResearches.add(orderResearch);
            }
            return orderResearches;
        }catch(SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
}
