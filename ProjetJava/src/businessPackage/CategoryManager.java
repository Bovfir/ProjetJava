package businessPackage;

import dataAccessPackage.*;
import modelPackage.*;
import exceptionPackage.*;
import java.util.ArrayList;

public class CategoryManager {
    private CategoryDataAccess dao;
    public CategoryManager() throws ConnectionException {
        setDao(new CategoryDBAccess());
    }
    public void setDao(CategoryDataAccess dao) {
        this.dao = dao;
    }
    public ArrayList<Category> getAllCategories() throws AllValuesException {
        return dao.getAllCategories();
    }
    public ArrayList<Category> getCategoryByBakery(String bakeryName) throws AllValuesException {
        return dao.getCategoryByBakery(bakeryName);
    }
}
