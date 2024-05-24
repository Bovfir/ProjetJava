package dataAccessPackage;

import modelPackage.Bakery;
import exceptionPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BakeryDBAccess implements BakeryDataAccess {
    private ArrayList<Bakery> bakeries;
    private Connection connection;
    public BakeryDBAccess() throws ConnectionException {

            connection = SingletonConnection.getInstance();
    }
    @Override
    public ArrayList<Bakery> getAllBakeries() throws AllValuesException {
        try {
            bakeries = new ArrayList<>();
            String sqlInstruction = "SELECT * FROM bakery";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            Bakery bakery;
            while (data.next()) {
                Integer bakery_id = data.getInt("bakery_id");
                String wording = data.getString("wording");
                String streetAndNumber = data.getString("street_and_number");
                Integer locality_id = data.getInt("locality_id");
                bakery = new Bakery(bakery_id,wording,streetAndNumber,locality_id);
                bakeries.add(bakery);
            }
            return bakeries;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
    @Override
    public ArrayList<Bakery> getBakeriesByLocalities(String locality) throws AllValuesException {
        try {
            bakeries = new ArrayList<>();
            String sqlInstruction = "SELECT * FROM bakery b inner join locality l on b.locality_id = l.locality_id  where l.wording = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, locality);
            ResultSet data = preparedStatement.executeQuery();

            Bakery bakery;
            while (data.next()) {
                bakery = new Bakery(data.getInt("bakery_id"), data.getString("wording"), data.getString("street_and_number"), data.getInt("locality_id"));
                bakeries.add(bakery);
            }
            return bakeries;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
}
