package businessPackage;

import dataAccessPackage.*;
import modelPackage.*;
import exceptionPackage.*;
import java.util.ArrayList;

public class LineManager {
    private LineDataAccess dao;
    public LineManager() throws ConnectionException {
        setDao(new LineDBAccess());
    }
    public void setDao(LineDataAccess dao) {
        this.dao = dao;
    }
    public void addLine(Line line) throws AddValueException {
        dao.addLine(line);
    }
    public ArrayList<LineResearch> getLineByCategory(String category) throws AllValuesException{
        return dao.getLineByCategory(category);
    }
}
