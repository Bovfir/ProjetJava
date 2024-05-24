package businessPackage;

import dataAccessPackage.*;
import modelPackage.Bakery;
import exceptionPackage.*;
import java.util.ArrayList;

public class BakeryManager {
    private BakeryDataAccess dao;
    public BakeryManager() throws ConnectionException {
        setDao(new BakeryDBAccess());
    }
    public void setDao(BakeryDBAccess dao) {
        this.dao = dao;
    }
    public ArrayList<Bakery> getAllBakeries() throws AllValuesException {
        return dao.getAllBakeries();
    }
    public ArrayList<Bakery> getBakeriesByLocalities(String locality) throws AllValuesException{
        return dao.getBakeriesByLocalities(locality);
    }
}
