package businessPackage;

import dataAccessPackage.*;
import exceptionPackage.*;
import modelPackage.*;

import java.util.ArrayList;

public class ProductManager {
    private ProductDataAccess dao;
    public ProductManager() throws ConnectionException {
        setDao(new ProductDBAccess());
    }
    public void setDao(ProductDataAccess dao) {
        this.dao = dao;
    }
    public ArrayList<Product> getProductsByCategoryAndBakery(String category, String bakery) throws AllValuesException {
        return dao.getProductsByCategoryAndBakery(category,bakery);
    }
}
