package dataAccessPackage;

import modelPackage.*;
import exceptionPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDBAccess implements CategoryDataAccess {
    private ArrayList<Category> categories;
    private Connection connection;
    public CategoryDBAccess() throws ConnectionException {
            connection = SingletonConnection.getInstance();
    }
    @Override
    public ArrayList<Category> getAllCategories() throws AllValuesException {
        try {
            categories = new ArrayList<>();
            String sqlInstrction = "SELECT * FROM category";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstrction);
            ResultSet data = preparedStatement.executeQuery();

            Category category;
            while (data.next()) {
                category = new Category(data.getInt("category_id"), data.getString("wording"), data.getString("information"));
                categories.add(category);
            }
            return categories;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
    @Override
    public ArrayList<Category> getCategoryByBakery(String bakeryName) throws AllValuesException {
        try {
            categories = new ArrayList<>();
            String sqlInstruction = "SELECT DISTINCT  c.*" +
                    "FROM category c inner join product p on c.category_id = p.category_id \n" +
                    "inner join bakery b on p.bakery_id = b.bakery_id \n" +
                    "WHERE b.wording = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, bakeryName);
            ResultSet data = preparedStatement.executeQuery();

            Category category;
            while (data.next()) {
                Integer categoryId = data.getInt("category_id");
                String wording = data.getString("wording");
                String information = data.getString("information");

                category = new Category(categoryId,wording,information);
                categories.add(category);
            }
            return categories;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
}