package dataAccessPackage;

import modelPackage.*;
import exceptionPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MeansOfPaymentBDAccess implements MeansOfPaymentDataAccess {
    private ArrayList<MeansOfPayment> meansOfPayments;
    private Connection connection;
    public MeansOfPaymentBDAccess() throws ConnectionException {
            connection = SingletonConnection.getInstance();
    }
    @Override
    public ArrayList<MeansOfPayment> getMeansOfPaymentsByCustomer(Integer registrationNumber) throws AllValuesException {
        try {
            meansOfPayments = new ArrayList<>();
            String sqlInstruction = "SELECT * FROM means_of_payment WHERE registration_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, registrationNumber);
            ResultSet data = preparedStatement.executeQuery();

            MeansOfPayment meansOfPayment;
            while (data.next()) {
                Integer meansOfPaymentId = data.getInt("means_of_payment_id");
                String wording = data.getString("wording");
                String information = data.getString("information");
                Integer typeId = data.getInt("type_id");
                registrationNumber = data.getInt("registration_number");
                meansOfPayment = new MeansOfPayment(meansOfPaymentId, wording, information, typeId, registrationNumber);

                Double amount = data.getDouble("amount");
                if (!data.wasNull()) {
                    meansOfPayment.setAmount(amount);
                }
                Integer cardNumber = data.getInt("card_number");
                if (!data.wasNull()) {
                    meansOfPayment.setCardNumber(cardNumber);
                }
                String paypalAccount = data.getString("paypal_account");
                if (!data.wasNull()) {
                    meansOfPayment.setPaypalAccount(paypalAccount);
                }
                meansOfPayments.add(meansOfPayment);
            }
            return meansOfPayments;
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
    @Override
    public TypeOfPayment getTypeOfPaymentById(Integer id) throws AllValuesException {
        try{
            String sqlInstruction = "SELECT t.type_id, t.wording FROM means_of_payment m inner join type t on m.type_id = t.type_id WHERE t.type_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            return new TypeOfPayment(data.getInt("type_id"), data.getString("wording"));
        }catch (SQLException exception){
            throw new AllValuesException(exception.getMessage());
        }
    }
    @Override
    public void modifyAmount(Double amount, Integer means_of_payment_id) throws UpdateValueException {
        try {
            String sqlInstruction = "UPDATE means_of_payment SET amount = ? WHERE means_of_payment_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2, means_of_payment_id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new UpdateValueException(exception.getMessage());
        }
    }
}