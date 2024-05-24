package businessPackage;

import dataAccessPackage.*;
import exceptionPackage.*;
import modelPackage.*;
import java.util.ArrayList;

public class MeansOfPaymentManager {
    private MeansOfPaymentDataAccess dao;
    public MeansOfPaymentManager() throws ConnectionException {
        setDao(new MeansOfPaymentBDAccess());
    }
    public void setDao(MeansOfPaymentBDAccess dao) {
        this.dao = dao;
    }
    public ArrayList<MeansOfPayment> getMeansOfPaymentsByCustomer(Integer registrationNumber) throws AllValuesException {
        return dao.getMeansOfPaymentsByCustomer(registrationNumber);
    }
    public TypeOfPayment getTypeOfPaymentById(Integer id) throws AllValuesException{
        return dao.getTypeOfPaymentById(id);
    }
    public void modifyAmount(Double amount, Integer means_of_payment_id) throws UpdateValueException{
        dao.modifyAmount(amount, means_of_payment_id);
    }
}
