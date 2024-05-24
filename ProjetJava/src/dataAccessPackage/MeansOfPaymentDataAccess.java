package dataAccessPackage;

import modelPackage.*;
import exceptionPackage.*;

import java.util.ArrayList;

public interface MeansOfPaymentDataAccess {
    ArrayList<MeansOfPayment> getMeansOfPaymentsByCustomer(Integer registrationNumber) throws AllValuesException;
    TypeOfPayment getTypeOfPaymentById(Integer id) throws AllValuesException;
    void modifyAmount(Double amount, Integer means_of_payment_id) throws UpdateValueException;
}
