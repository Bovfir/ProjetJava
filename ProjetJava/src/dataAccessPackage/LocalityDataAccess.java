package dataAccessPackage;

import modelPackage.Locality;
import exceptionPackage.*;
import modelPackage.WorkersByLocality;

import java.util.ArrayList;

public interface LocalityDataAccess {

    ArrayList<Locality> getAllLocalities() throws AllValuesException;
    ArrayList<Locality> getLocalityWithBakery() throws AllValuesException;
    ArrayList<WorkersByLocality> getWorkersByLocality(ArrayList<Integer> localities2) throws AllValuesException;
    ArrayList<Integer> getAllPostalCodes() throws AllValuesException;
}
