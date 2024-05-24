package businessPackage;

import dataAccessPackage.*;
import exceptionPackage.*;
import modelPackage.*;
import java.util.ArrayList;

public class LocalityManager {
    private LocalityDataAccess dao;
    public LocalityManager() throws ConnectionException {
        setDao(new LocalityDBAccess());
    }
    public void setDao(LocalityDataAccess dao) {
        this.dao = dao;
    }
    public ArrayList<Locality> getAllLocalities() throws AllValuesException {
        return dao.getAllLocalities();
    }
    public ArrayList<Locality> getLocalityWithBakery() throws AllValuesException{
        return dao.getLocalityWithBakery();
    }
    public ArrayList<WorkersByLocality> getWorkersByLocality(ArrayList<Integer> localities2) throws AllValuesException{
        return dao.getWorkersByLocality(localities2);
    }
    public ArrayList<Integer> getAllPostalCodes() throws AllValuesException{
        return dao.getAllPostalCodes();
    }

}
