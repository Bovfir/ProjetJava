package dataAccessPackage;

import modelPackage.Product;
import exceptionPackage.*;

import java.util.ArrayList;

public interface ProductDataAccess {
    ArrayList<Product> getProductsByCategoryAndBakery(String category, String bakery) throws AllValuesException;
}
