package dataAccessPackage;

import modelPackage.*;

import exceptionPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerDBAccess implements CustomerDataAccess {
    private ArrayList<Customer> customers;
    private Connection connection;

    public CustomerDBAccess() throws ConnectionException {
            connection = SingletonConnection.getInstance();
    }
    @Override
    public ArrayList<Customer> getAllCustomers() throws AllValuesException {
        try {
            customers = new ArrayList<>();
            String sqlInstruction = "SELECT * FROM customer c inner join person p on c.registration_number = p.registration_number";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            Customer customer;
            while (data.next()) {
                Integer registrationNumber = data.getInt("registration_number");
                String lastName = data.getString("last_name");
                String firstName = data.getString("first_name");
                String email = data.getString("email");
                LocalDate birthday = data.getDate("birthday").toLocalDate();
                Integer locality = data.getInt("locality_id");
                Integer loyalityCard = data.getInt("loyalty_card_id");

                try {
                    customer = new Customer(registrationNumber,lastName,firstName,email,birthday,locality,loyalityCard);
                    if (data.getObject("address") != null) {
                        customer.setAddress(data.getString("address"));
                    }
                    if (data.getObject("phone_number") != null) {
                        customer.setPhoneNumber(data.getInt("phone_number"));
                    }
                    customers.add(customer);
                }catch (SettorException exception){
                    throw new AllValuesException(exception.getMessage());
                }
            }
            return customers;
        } catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
}