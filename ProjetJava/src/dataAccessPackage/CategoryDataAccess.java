package dataAccessPackage;

import modelPackage.Category;
import exceptionPackage.*;

import java.util.ArrayList;

public interface CategoryDataAccess {

    ArrayList<Category> getAllCategories() throws AllValuesException;
    ArrayList<Category> getCategoryByBakery(String bakeryName) throws AllValuesException;
}
