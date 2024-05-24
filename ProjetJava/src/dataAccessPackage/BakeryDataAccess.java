package dataAccessPackage;

import modelPackage.Bakery;
import exceptionPackage.*;
import java.util.ArrayList;

public interface BakeryDataAccess {

    ArrayList<Bakery> getAllBakeries() throws AllValuesException;
    ArrayList<Bakery> getBakeriesByLocalities(String locality) throws AllValuesException;
}
