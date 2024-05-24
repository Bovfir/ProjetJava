package dataAccessPackage;

import modelPackage.*;
import exceptionPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class ProductDBAccess implements ProductDataAccess {
    private ArrayList<Product> products;
    private Connection connection;

    public ProductDBAccess() throws ConnectionException {
            connection = SingletonConnection.getInstance();
    }
    @Override
    public ArrayList<Product> getProductsByCategoryAndBakery(String category,String bakery) throws AllValuesException {
        try {
            products = new ArrayList<>();
            String sqlInstruction = "SELECT * \n" +
                    "    FROM product p inner join category c on p.category_id = c.category_id\n" +
                    "inner join bakery b on p.bakery_id = b.bakery_id\n" +
                    "WHERE c.wording = ? and b.wording = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, category);
            preparedStatement.setString(2, bakery);
            ResultSet data = preparedStatement.executeQuery();

            Product product;
            while (data.next()) {
                Integer productCode = data.getInt("product_code");
                String wording = data.getString("wording");
                Double price = data.getDouble("price");
                LocalDate sellingDate = data.getDate("selling_date").toLocalDate();
                Boolean mustBePrepaid = data.getBoolean("must_be_prepaid");
                Boolean isAvailable = data.getBoolean("is_available");
                Integer categoryId = data.getInt("category_id");
                Integer bakeryId = data.getInt("bakery_id");
                Integer minNumber = data.getInt("min_number");
                Integer maxNumber = data.getInt("max_number");

                product = new Product(productCode, wording , price, sellingDate,mustBePrepaid, isAvailable, categoryId, bakeryId,minNumber,maxNumber);
                Integer delay = data.getInt("delay");
                if (!data.wasNull()) {
                    product.setDelay(delay);
                }
                products.add(product);
            }
            return products;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
}