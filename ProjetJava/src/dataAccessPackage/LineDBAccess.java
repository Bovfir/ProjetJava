package dataAccessPackage;

import modelPackage.*;
import exceptionPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LineDBAccess implements LineDataAccess {
    private Connection connection;
    private ArrayList<LineResearch> lineResearches;

    public LineDBAccess() throws ConnectionException {
            connection = SingletonConnection.getInstance();
    }
    @Override
    public void addLine(Line line) throws AddValueException {
        try {
            String sqlInstruction = "INSERT INTO line (quantity, order_number, product_code, price) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, line.getQuantity());
            preparedStatement.setInt(2, line.getOrder());
            preparedStatement.setInt(3, line.getProduct());
            preparedStatement.setDouble(4, line.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new AddValueException(exception.getMessage());
        }
    }
    @Override
    public ArrayList<LineResearch> getLineByCategory(String category) throws AllValuesException {
        try {
            lineResearches = new ArrayList<>();
            String sqlInstructuon = "SELECT c.order_date, l.quantity, p.wording as 'product', b.wording as 'bakery'\n" +
                    "FROM line l inner join command c on l.order_number = c.order_number\n" +
                    "inner join product p on l.product_code = p.product_code\n" +
                    "            inner join bakery b on b.bakery_id = p.bakery_id\n" +
                    "inner join category cgy on p.category_id = cgy.category_id\n" +
                    "where cgy.wording = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstructuon);
            preparedStatement.setString(1, category);
            ResultSet data = preparedStatement.executeQuery();

            LineResearch lineResearch;
            while(data.next()){
                Integer quantity = data.getInt("quantity");
                String product = data.getString("product");
                String bakery = data.getString("bakery");
                lineResearch = new LineResearch(data.getDate("order_date").toLocalDate(), quantity, product, bakery);
                lineResearches.add(lineResearch);
            }
            return lineResearches;
        }catch(SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
}
